package com.frakseno.model;

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

    private String address;

    private String city;

    private String state;

    @NotNull
    private String zipCode;

    @NotNull
    @ManyToOne()
    private Neighborhood neighborhood;
}