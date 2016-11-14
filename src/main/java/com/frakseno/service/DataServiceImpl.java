package com.frakseno.service;

import com.frakseno.model.Museum;
import com.frakseno.model.Neighborhood;
import com.frakseno.model.Restaurant;
import com.frakseno.repository.MuseumRepository;
import com.frakseno.repository.NeighborhoodRepository;
import com.frakseno.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("dataService")
@Transactional
public class DataServiceImpl implements DataService{
    @Autowired
    private NeighborhoodRepository neighborhoodRepository;

    @Autowired
    private MuseumRepository museumRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Iterable<Neighborhood> getNeighborhoods() {
        return neighborhoodRepository.findAll();
    }

    @Override
    public Iterable<Museum> getMuseums() {
        return museumRepository.findAll();
    }

    @Override
    public Iterable<Restaurant> getRestaurantsForNeighborhood(Long neighbordhoodId) {
        Neighborhood neighborhood = neighborhoodRepository.findOne(neighbordhoodId);

        return restaurantRepository.findByNeighborhood(neighborhood);
    }

    @Override
    public Restaurant getRestaurantDetails(Long restaurantId) {
        return restaurantRepository.findOne(restaurantId);
    }

    @Override
    public Restaurant saveNewRestaurant(Restaurant restaurant) {
        Neighborhood neighborhood = neighborhoodRepository.findOne(restaurant.getNeighborhood().getId());

        restaurant.setNeighborhood(neighborhood);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantRepository.findOne(restaurantId);

        restaurant.setName(updatedRestaurant.getName());
        restaurant.setZipCode(updatedRestaurant.getZipCode());
        restaurant.setAddress(updatedRestaurant.getAddress());
        restaurant.setCity(updatedRestaurant.getCity());
        restaurant.setState(updatedRestaurant.getState());

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        restaurantRepository.delete(restaurantId);
    }
}
