package com.frakseno.service;

import com.frakseno.model.Building;
import com.frakseno.model.BuildingType;
import com.frakseno.model.Neighborhood;
import com.frakseno.repository.BuildingRepository;
import com.frakseno.repository.NeighborhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("dataService")
@Transactional
public class DataServiceImpl implements DataService{
    @Autowired
    private NeighborhoodRepository neighborhoodRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public Iterable<Neighborhood> getNeighborhoods() {
        return neighborhoodRepository.findAll();
    }

    @Override
    public Iterable<Building> getMuseums() {
        return buildingRepository.findByBuildingTypeOrderByName(BuildingType.MUSEUM);
    }

    @Override
    public Iterable<Building> getRestaurantsForNeighborhood(Long neighbordhoodId) {
        Neighborhood neighborhood = neighborhoodRepository.findOne(neighbordhoodId);

        return buildingRepository.findByNeighborhoodAndBuildingTypeOrderByName(neighborhood, BuildingType.RESTAURANT);
    }

    @Override
    public Building getRestaurantDetails(Long restaurantId) {
        return buildingRepository.findOne(restaurantId);
    }

    @Override
    public Building saveNewRestaurant(Building restaurant) {
        Neighborhood neighborhood = neighborhoodRepository.findOne(restaurant.getNeighborhood().getId());

        restaurant.setNeighborhood(neighborhood);

        return buildingRepository.save(restaurant);
    }

    @Override
    public Building updateRestaurant(Long restaurantId, Building updatedRestaurant) {
        Building restaurant = buildingRepository.findOne(restaurantId);

        restaurant.setName(updatedRestaurant.getName());
        restaurant.setZipCode(updatedRestaurant.getZipCode());
        restaurant.setStreetNumber(updatedRestaurant.getStreetNumber());
        restaurant.setStreetName(updatedRestaurant.getStreetName());
        restaurant.setCity(updatedRestaurant.getCity());
        restaurant.setState(updatedRestaurant.getState());

        return buildingRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        buildingRepository.delete(restaurantId);
    }
}
