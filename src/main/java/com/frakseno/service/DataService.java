package com.frakseno.service;

import com.frakseno.model.Building;
import com.frakseno.model.Neighborhood;

public interface DataService {
    public Iterable<Neighborhood> getNeighborhoods();

    public Iterable<Building> getMuseums();

    public Iterable<Building> getRestaurantsForNeighborhood(Long neighbordhoodId);

    public Building getRestaurantDetails(Long restaurantId);

    public Building saveNewRestaurant(Building restaurant);

    public Building updateRestaurant(Long restaurantId, Building restaurant);

    public void deleteRestaurant(Long restaurantId);
}
