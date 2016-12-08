package com.frakseno.repository;

import com.frakseno.model.Building;
import com.frakseno.model.BuildingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface BuildingRepository extends JpaRepository<Building, Long> {
    Collection<Building> findByBuildingTypeOrderByName(@Param("buildingType") BuildingType buildingType);

    Collection<Building> findByNeighborhoodNameAndBuildingTypeOrderByName(@Param("neighborhoodName") String neighborhoodName,
                                                                        @Param("buildingType") BuildingType buildingType);
}
