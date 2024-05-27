package com.brasens.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import static com.brasens.Commons.DEFAULT_TIMEZONE;

@Entity
@Table(name="client")
public class ClientModel {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "name",unique = true)
    private String login;
    
    @Column(name = "email",unique = true)
    private String email;

	@ManyToOne
	@JoinColumn(name = "id_role")
	private Role role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
	@Column(name = "created_at")
	private ZonedDateTime created_at = ZonedDateTime.now(DEFAULT_TIMEZONE.toZoneId());

	@ManyToOne
	@JoinColumn(name = "organization_id")
	@JsonIgnore
	private Organization organization;

	public ClientModel(UUID id, String login, String email, String password, ZonedDateTime created_at) {
		super();
		this.id = id;
		this.login = login;
		this.email = email;
		this.password = password;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public ClientModel() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ZonedDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(ZonedDateTime created_at) {
		this.created_at = created_at;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
