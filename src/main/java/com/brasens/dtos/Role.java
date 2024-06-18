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
@Table(name="Roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    private String role;
    private String color;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<Employees> employees = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "organization_id")
    @JsonIgnore
    private Organization organization;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "roles_privileges",
            joinColumns = { @JoinColumn(name = "id_role") },
            inverseJoinColumns = { @JoinColumn(name = "id_privilege") })
    private List<Privilege> privileges = new ArrayList<>();
}