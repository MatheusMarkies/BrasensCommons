package com.brasens.dtos;

import com.brasens.dtos.enums.AlertLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="Alert")
@TypeDef(
        name = "alertlevel",
        typeClass = PostgreSQLEnumType.class
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Alert {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    @Column(name = "alert_value")
    private double value;
    @Column(name = "alert_critical_value")
    private double criticalValue;

    @Column(name = "alert_level")
    @Enumerated(EnumType.STRING)
    @Type( type = "alertlevel")
    private AlertLevel level;

    @Column(name = "alert_tags")
    private String tags;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "id_asset", nullable = false)
    @JsonIgnore
    private Asset asset;
}
