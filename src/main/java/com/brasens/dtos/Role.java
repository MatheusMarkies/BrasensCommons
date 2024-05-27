package com.brasens.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="roles")
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
    private List<ClientModel> clients = new ArrayList<>();

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

    public Role() {
    }

    public Role(UUID id, String role, String color, List<ClientModel> clients, Organization organization, List<Privilege> privileges) {
        this.id = id;
        this.role = role;
        this.color = color;
        this.clients = clients;
        this.organization = organization;
        this.privileges = privileges;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<ClientModel> getClients() {
        return clients;
    }

    public void setClients(List<ClientModel> clients) {
        this.clients = clients;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}