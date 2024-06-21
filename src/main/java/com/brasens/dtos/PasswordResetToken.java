package com.brasens.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="Password_Reset_Token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetToken {

    public static final int EXPIRATION = 60 * 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Employees.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Employees client;

    private Date expiryDate;

    public boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    public boolean isTokenExpired() {
        final Calendar cal = Calendar.getInstance();
        return expiryDate.before(cal.getTime());
    }

    public PasswordResetToken(String token, Employees client) {
        this.token = token;
        this.client = client;
    }
}