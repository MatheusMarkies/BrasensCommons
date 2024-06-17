package com.brasens.math;

import java.util.ArrayList;
import java.util.List;

public class SimpleSearch {

    public static List<String> compare(String compareWord, List<String> words){
        List<String> compared = new ArrayList<>();

        for(String s : words) {
            int k = 0;
            for (int i = 0; i < s.length(); i++) {
                for (int g = 0; g < compareWord.length(); g++)
                    if (s.charAt(i) == compareWord.charAt(g)) {
                        k++;
                    }else break;
                if(k <= compareWord.length()-1){
                    compared.add(s);
                }
            }
        }

        return compared;
    }

}
