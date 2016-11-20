package com.frakseno.repository;

import com.frakseno.model.Building;
import com.frakseno.model.BuildingType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    Collection<Building> findByBuildingTypeOrderByName(BuildingType buildingType);

    Collection<Building> findByNeighborhoodIdAndBuildingTypeOrderByName(Long neighborhoodId, BuildingType buildingType);
}
