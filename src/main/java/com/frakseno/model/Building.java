package com.frakseno.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "buildings")
@Data
@NoArgsConstructor
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

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

    @NotNull
    @ManyToOne
    private Neighborhood neighborhood;
}