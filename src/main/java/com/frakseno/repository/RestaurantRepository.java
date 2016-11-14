package com.frakseno.repository;

import com.frakseno.model.Neighborhood;
import com.frakseno.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Collection<Restaurant> findByNeighborhood(Neighborhood neighborhood);
}
