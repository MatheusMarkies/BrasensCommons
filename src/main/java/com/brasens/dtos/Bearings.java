package com.brasens.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="Bearings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bearings {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    @Column(name = "brand")
    String Brand;

    @Column(name = "identifier")
    String Identifier;

    @Column(name = "element_amount")
    int ElementAmount;

    double FTF;
    double BSF;
    double BPFO;
    double BPFI;
}
