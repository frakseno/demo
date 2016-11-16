package com.frakseno.service;

import com.frakseno.model.Restaurant;

/**
 * Created by ashneyder on 11/14/16.
 */
public interface LocationService {
    public void verifyRestaurantLocation(Restaurant originalLocation) throws Exception;
}
