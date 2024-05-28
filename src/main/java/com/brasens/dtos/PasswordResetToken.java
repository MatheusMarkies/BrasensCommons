package com.brasens.dtos;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class PasswordResetToken {

    public static final int EXPIRATION = 60 * 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = ClientModel.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private ClientModel client;

    private Date expiryDate;

    public PasswordResetToken(String token, ClientModel client) {
        this.token = token;
        this.client = client;
    }

    public boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    public boolean isTokenExpired() {
        final Calendar cal = Calendar.getInstance();
        return expiryDate.before(cal.getTime());
    }

    public PasswordResetToken() {
    }

    public PasswordResetToken(Long id, String token, ClientModel client, Date expiryDate) {
        this.id = id;
        this.token = token;
        this.client = client;
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}