package com.brasens.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="organization")
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
    private List<ClientModel> clients = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.DETACH, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Asset> assets = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.DETACH, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Workorder> workorder = new ArrayList<>();

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                ", clients=" + clients +
                ", assets=" + assets +
                ", workorder=" + workorder +
                '}';
    }

    public Organization() {
    }

    public Organization(UUID id, String name, List<Role> roles, List<ClientModel> clients, List<Asset> assets, List<Workorder> workorder) {
        this.id = id;
        this.name = name;
        this.roles = roles;
        this.clients = clients;
        this.assets = assets;
        this.workorder = workorder;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<ClientModel> getClients() {
        return clients;
    }

    public void setClients(List<ClientModel> clients) {
        this.clients = clients;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Workorder> getWorkorder() {
        return workorder;
    }

    public void setWorkorder(List<Workorder> workorder) {
        this.workorder = workorder;
    }
}
