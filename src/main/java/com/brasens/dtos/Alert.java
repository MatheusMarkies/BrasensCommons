package com.brasens.dtos;

import com.brasens.dtos.enums.AlertLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name="Alert")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    @Type( type = "alertlevel")
    private AlertLevel level;

    @OneToMany(targetEntity = AlertComments.class, mappedBy = "alert", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AlertComments> alertComments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "asset_id", nullable = false)
    @JsonIgnore
    private AssetSummaryDTO asset;
}