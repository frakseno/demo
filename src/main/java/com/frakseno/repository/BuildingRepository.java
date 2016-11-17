package com.frakseno.repository;

import com.frakseno.model.Building;
import com.frakseno.model.BuildingType;
import com.frakseno.model.Neighborhood;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface BuildingRepository extends CrudRepository<Building, Long> {
    Collection<Building> findByBuildingTypeOrderByName(BuildingType buildingType);
    Collection<Building> findByNeighborhoodAndBuildingTypeOrderByName(Neighborhood neighborhood, BuildingType buildingType);
}
