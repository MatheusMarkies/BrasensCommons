package com.brasens.dtos;

import com.brasens.dtos.enums.AssetState;
import com.brasens.dtos.enums.DowntimeType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.brasens.Commons.DEFAULT_TIMEZONE;

@Entity
@Table(name="Downtime")
@TypeDef(
        name = "assetstate",
        typeClass = PostgreSQLEnumType.class
)
@TypeDef(
        name = "downtimetype",
        typeClass = PostgreSQLEnumType.class
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Downtime {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    @Column(name = "downtime")
    private double downtime;

    @Column(name = "downtime_type")
    @Enumerated(EnumType.STRING)
    @Type( type = "downtimetype")
    private DowntimeType downtime_type;

    @Column(name = "asset_state")
    @Enumerated(EnumType.STRING)
    @Type( type = "assetstate")
    private AssetState assetState;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    @JsonIgnore
    private Asset asset;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
    @Column(name = "added", insertable = false, updatable = false)
    private ZonedDateTime added = ZonedDateTime.now().withZoneSameInstant(DEFAULT_TIMEZONE.toZoneId());
}
