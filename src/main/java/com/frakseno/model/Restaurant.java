package com.frakseno.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "restaurants")
@Data
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String zipCode;

    @Transient
    @JsonIgnore
    private String fullAddress;

    private String streetNumber;

    private String streetName;

    private String city;

    private String state;

    private Double latitude;

    private Double longitude;

    @ManyToOne
    private Neighborhood neighborhood;
}
