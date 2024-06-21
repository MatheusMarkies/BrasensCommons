package com.brasens.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="Organization")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Organization {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.DETACH, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.DETACH, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Employees> employees = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.DETACH, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Asset> assets = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.DETACH, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Workorder> workorder = new ArrayList<>();
}

