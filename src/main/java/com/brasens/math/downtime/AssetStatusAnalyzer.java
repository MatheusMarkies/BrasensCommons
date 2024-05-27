package com.brasens.math.downtime;

import com.brasens.dtos.MachineIntervals;
import com.brasens.dtos.enums.AssetState;
import com.brasens.dtos.enums.DataPeriod;
import com.brasens.response.AnalyzedData;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.*;

import static com.brasens.Commons.DEFAULT_TIMEZONE;

public class AssetStatusAnalyzer {

    public static AnalyzedData processData(List<MachineIntervals> machineIntervals, DataPeriod dataPeriod) {
        List<String> timeTags = new ArrayList<>();
        List<Double> offlineTime = new ArrayList<>();
        List<Double> onlineTime = new ArrayList<>();
        ZonedDateTime now = ZonedDateTime.now().withZoneSameInstant(
                ZoneId.of("America/Sao_Paulo")
        );

        List<MachineIntervals> intervalsWithTimeZone = new ArrayList<>();

        for (MachineIntervals i : machineIntervals) {
            i.setAdded(i.getAdded().withZoneSameInstant(
                    ZoneId.of("America/Sao_Paulo")
            ));
            intervalsWithTimeZone.add(i);
        }

        Comparator<MachineIntervals> comparator = Comparator.comparing(MachineIntervals::getAdded);
        intervalsWithTimeZone.sort(comparator);

        for (MachineIntervals i : intervalsWithTimeZone)
            System.out.println(i.getAdded());

        double maxValue = 0;

        List<ZonedDateTime> bounds = new ArrayList<>();
        int periodCount = 0;
        switch (dataPeriod) {
            case DAILY:
                periodCount = now.getHour();
                for (int i = 0; i <= periodCount; i++) {
                    bounds.add(now.minusHours(periodCount - i));

                    String hourA = "";
                    String hourB = "";

                    String minuteA = "";
                    String minuteB = "";

                    if (now.minusHours(periodCount - i).getHour() < 10)
                        hourA = "0";
                    hourA += now.minusHours(periodCount - i).getHour();
                    if (now.minusHours(periodCount - i - 1).getHour() < 10)
                        hourB = "0";
                    hourB += now.minusHours(periodCount - i - 1).getHour();

                    if (now.minusHours(periodCount - i).getMinute() < 10)
                        minuteA = "0";
                    minuteA += now.minusHours(periodCount - i).getMinute();
                    if (now.minusHours(periodCount - i - 1).getMinute() < 10)
                        minuteB = "0";
                    minuteB += now.minusHours(periodCount - i - 1).getMinute();

                    String boundA = hourA + "h" + minuteA;
                    String boundB = hourB + "h" + minuteB;

                    timeTags.add(boundA + "-" + boundB);
                    offlineTime.add(0.0);
                    onlineTime.add(0.0);
                }
                maxValue = 1.0;
                break;
            case WEEKLY:
                periodCount = 7;
                for (int i = 0; i < periodCount; i++) {
                    bounds.add(now.minusDays(i));

                    String boundA = DayOfWeek.of((now.minusDays(i).getDayOfWeek().getValue()))
                            .getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
                    String boundB = DayOfWeek.of((now.minusDays(i - 1).getDayOfWeek().getValue()))
                            .getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

                    timeTags.add(boundA + " - " + boundB);

                    offlineTime.add(0.0);
                    onlineTime.add(0.0);
                    //System.out.println(now.minusDays(i).getDayOfWeek().getValue());
                    //System.out.println(DayOfWeek.of((now.minusDays(i).getDayOfWeek().getValue()))
                    // .getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
                }
                Collections.reverse(bounds);
                Collections.reverse(timeTags);
                maxValue = 24.0;
                break;
            case MONTHLY:
                LocalDate firstDayOfMonth = now.toLocalDate().withDayOfMonth(1);
                int firstWeekOfMonth = firstDayOfMonth.get(WeekFields.of(Locale.getDefault()).weekOfYear());
                int currentWeekOfYear = now.get(WeekFields.of(Locale.getDefault()).weekOfYear());

                //System.out.println(firstWeekOfMonth);
                //System.out.println(currentWeekOfYear);

                periodCount = currentWeekOfYear - firstWeekOfMonth + 1;
                //System.out.println(periodCount);
                for (int i = 0; i < periodCount; i++) {
                    bounds.add(now.minusWeeks(periodCount - (i + 1)));

                    String dayA = "";
                    String dayB = "";

                    String monthA = "";
                    String monthB = "";

                    if (now.minusWeeks(periodCount - (i + 1)).getDayOfMonth() < 10)
                        dayA = "0";
                    dayA += now.minusWeeks(periodCount - (i + 1)).getDayOfMonth();
                    if (now.minusWeeks(periodCount - (i + 1) - 1).getDayOfMonth() < 10)
                        dayB = "0";
                    dayB += now.minusWeeks(periodCount - (i + 1) - 1).getDayOfMonth();

                    if (now.minusWeeks(periodCount - (i + 1)).getMonthValue() < 10)
                        monthA = "0";
                    monthA += now.minusWeeks(periodCount - (i + 1)).getMonthValue();
                    if (now.minusWeeks(periodCount - (i + 1) - 1).getMonthValue() < 10)
                        monthB = "0";
                    monthB += now.minusWeeks(periodCount - (i + 1) - 1).getMonthValue();

                    String boundA = dayA + "\\" + monthA;
                    String boundB = dayB + "\\" + monthB;

                    timeTags.add(boundA + " - " + boundB);

                    offlineTime.add(0.0);
                    onlineTime.add(0.0);
                }
                maxValue = 168.0;
                break;
            default:
                return processData(machineIntervals, DataPeriod.WEEKLY);
        }

        timeTags.remove(timeTags.size() - 1);
        offlineTime.remove(offlineTime.size() - 1);
        onlineTime.remove(onlineTime.size() - 1);

        int firstBound = 0;
        int index = 0;

        boolean terminateFor = false;
        for (int i = 1; i < bounds.size(); i++) {
            if (!terminateFor)
                for (MachineIntervals intervals : intervalsWithTimeZone) {
                    if (intervals.getAdded().isBefore(bounds.get(i))) {
                        firstBound = i - 1;
                        terminateFor = true;
                        break;
                    }
                }
        }

        for (int k = 0; k < intervalsWithTimeZone.size(); k++) {
            if (intervalsWithTimeZone.get(k).getAdded().isBefore(bounds.get(firstBound))) {
                index = k;
            }
        }

        int indexBound = firstBound + 1;

        System.out.println();
        System.out.println("index: "+ index + " | " + intervalsWithTimeZone.get(index));
        System.out.println("indexBound: "+ indexBound + " | " + bounds.get(indexBound));
        System.out.println("Bounds: "+ bounds.size());

        ZonedDateTime current = ZonedDateTime.now(DEFAULT_TIMEZONE.toZoneId());
        ZonedDateTime next = ZonedDateTime.now(DEFAULT_TIMEZONE.toZoneId());
        AssetState state = AssetState.IDLE;

        intervalsWithTimeZone.add(
                new MachineIntervals(
                        now,
                        intervalsWithTimeZone.get(intervalsWithTimeZone.size()-1).getAssetState()
                ));

        boolean firstPass = true;
            while ((index + 1) < (intervalsWithTimeZone.size())) {
                try {
                    state = intervalsWithTimeZone.get(index).getAssetState();
                    System.out.println();
                    //if ((index + 1) < (intervalsWithTimeZone.size() - 1)) {
                    if (intervalsWithTimeZone.get(index + 1).getAdded().isAfter(bounds.get(indexBound))) {
                        current = bounds.get(indexBound);
                        if (intervalsWithTimeZone.get(index + 1).getAdded().isBefore(bounds.get(indexBound + 1)))
                            next = intervalsWithTimeZone.get(index + 1).getAdded();
                        else
                            next = bounds.get(indexBound + 1);
                        System.out.println("Change Bound");
                        indexBound++;
                    } else {
                        next = intervalsWithTimeZone.get(index + 1).getAdded();
                        if (!firstPass)
                            current = intervalsWithTimeZone.get(index).getAdded();
                        else {
                            //if(dataPeriod != DataPeriod.MONTHLY)
                            //current = bounds.get(indexBound - 1);
                            //else
                            current = intervalsWithTimeZone.get(index).getAdded();
                            firstPass = false;
                        }
                        System.out.println("Change Index");
                        index++;
                    }
                    //} else break;

                    System.out.println("current: "+ current);
                    System.out.println("next: "+ next);

                    long deltaTime = ChronoUnit.MILLIS.between(current, next);

                    double deltaHours = (double) deltaTime / 60000.0;
                    deltaHours /= 60.0;
                    deltaHours = Math.abs(Math.floor(deltaHours * 1000.0) / 1000.0);
                    System.out.println("deltaHours: "+ deltaHours);
                    if (state == AssetState.WORKING) {
                        onlineTime.set(indexBound - 1, Math.min((onlineTime.get(indexBound - 1) + deltaHours), maxValue));
                    } else {
                        offlineTime.set(indexBound - 1, Math.min((offlineTime.get(indexBound - 1) + deltaHours), maxValue));
                    }
                } catch (Exception e) {
                    System.err.println(e);
                    break;
                }
            }

        double ao = 0;
        double bo = 0;
        for (int i = 0; i < onlineTime.size(); i++) {
            ao += onlineTime.get(i);
            bo += offlineTime.get(i);
        }
        System.out.println("a on: "+ ao);
        System.out.println("b: "+ bo);

        for (int i = 0; i < onlineTime.size(); i++) {
            if ((onlineTime.get(i) + offlineTime.get(i)) > maxValue) {
                double diff = (onlineTime.get(i) + offlineTime.get(i)) - maxValue;
                double A = onlineTime.get(i)/(onlineTime.get(i) + offlineTime.get(i));
                double B = offlineTime.get(i)/(onlineTime.get(i) + offlineTime.get(i));

                onlineTime.set(i, onlineTime.get(i) - A * diff);
                offlineTime.set(i, offlineTime.get(i) - B * diff);
            } else if ((onlineTime.get(i) + offlineTime.get(i)) > 0 && (onlineTime.get(i) + offlineTime.get(i)) < maxValue) {
                if(dataPeriod == DataPeriod.DAILY) {
                    double diff = -(onlineTime.get(i) + offlineTime.get(i)) + maxValue;
                    double A = onlineTime.get(i)/(onlineTime.get(i) + offlineTime.get(i));
                    double B = offlineTime.get(i)/(onlineTime.get(i) + offlineTime.get(i));

                    onlineTime.set(i, onlineTime.get(i) + A * diff);
                    offlineTime.set(i, offlineTime.get(i) + B * diff);
                    System.out.println("Corrections");
                }
            }
        }

        AnalyzedData sensorData = new AnalyzedData();
        sensorData.setTimeTags(timeTags.toArray(new String[0]));
        sensorData.setOfflineTime(offlineTime.stream().mapToDouble(Double::doubleValue).toArray());
        sensorData.setOnlineTime(onlineTime.stream().mapToDouble(Double::doubleValue).toArray());

        sensorData.setDowntime(bo);
        sensorData.setOntime(ao);

        return sensorData;
    }
}