package com.frakseno.service;

import com.frakseno.model.Museum;
import com.frakseno.model.Neighborhood;
import com.frakseno.model.Restaurant;

public interface DataService {
    public Iterable<Neighborhood> getNeighborhoods();

    public Iterable<Museum> getMuseums();

    public Iterable<Restaurant> getRestaurantsForNeighborhood(Long neighbordhoodId);

    public Restaurant getRestaurantDetails(Long restaurantId);

    public Restaurant saveNewRestaurant(Restaurant restaurant);

    public Restaurant updateRestaurant(Long restaurantId, Restaurant restaurant);

    public void deleteRestaurant(Long restaurantId);
}
