package com.frakseno.controller;

import com.frakseno.model.Building;
import com.frakseno.model.BuildingType;
import com.frakseno.model.Neighborhood;
import com.frakseno.repository.BuildingRepository;
import com.frakseno.repository.NeighborhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RestController
public class DataController {
//    @Autowired
//    private NeighborhoodRepository neighborhoodRepository;
//
//    @Autowired
//    private BuildingRepository buildingRepository;
//
//    @RequestMapping(value = "/neighborhoods", method = RequestMethod.GET)
//    public Iterable<Neighborhood> getNeighborhoods() {
//        return neighborhoodRepository.findAll();
//    }
//
//    @RequestMapping(value = "/buildings/search/findMuseums", method = RequestMethod.GET)
//    public Iterable<Building> getMuseums() {
//        return buildingRepository.findByBuildingTypeOrderByName(BuildingType.MUSEUM);
//    }
//
//    @RequestMapping(value = "/buildings/search/findRestaurantsForNeighborhood/{id}", method = RequestMethod.GET)
//    public Iterable<Building> getRestaurantsForNeighborhood(@PathVariable("id") Long neighborhoodId) {
//        return buildingRepository.findByNeighborhoodIdAndBuildingTypeOrderByName(neighborhoodId, BuildingType.RESTAURANT);
//    }
//
//    @RequestMapping(value = "/buildings", method = RequestMethod.POST)
//    public Building saveNewBuilding(@RequestBody Building building) {
//        return buildingRepository.save(building);
//    }
//
//    @RequestMapping(value = "/buildings/{id}", method = RequestMethod.GET)
//    public Building getBuilding(@PathVariable("id") Long id) {
//        return buildingRepository.getOne(id);
//    }
//
//    @RequestMapping(value = "/buildings/{id}", method = RequestMethod.PUT)
//    public Building updateBuilding(@PathVariable("id") Long id, @RequestBody Building building) {
//        building.setId(id);
//        return buildingRepository.save(building);
//    }
//
//    @RequestMapping(value = "/buildings/{id}", method = RequestMethod.DELETE)
//    public void deleteBuilding(@PathVariable("id") Long id) {
//        buildingRepository.delete(id);
//    }
}