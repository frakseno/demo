package com.frakseno.service;

import com.frakseno.model.Restaurant;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * Created by ashneyder on 11/14/16.
 */
@Service("locationService")
public class LocationServiceImpl implements LocationService {
    @Autowired
    GeoApiContext geoApiContext;


    public void verifyRestaurantLocation(Restaurant restaurant) throws Exception {
        GeocodingResult result =  GeocodingApi.geocode(geoApiContext, restaurant.getFullAddress()).await()[0];

        restaurant.setLatitude(result.geometry.location.lat);
        restaurant.setLongitude(result.geometry.location.lng);

        for(AddressComponent addressComponent : result.addressComponents) {
            AddressComponentType addressComponentType = addressComponent.types[0];
            switch (addressComponentType) {
                case STREET_NUMBER:
                    restaurant.setStreetNumber(addressComponent.longName);
                    break;
                case ROUTE:
                    restaurant.setStreetName(addressComponent.longName);
                    break;
                case LOCALITY:
                    restaurant.setCity(addressComponent.longName);
                    break;
                case ADMINISTRATIVE_AREA_LEVEL_1:
                    restaurant.setState(addressComponent.shortName);
                    break;
                case POSTAL_CODE:
                    restaurant.setZipCode(addressComponent.shortName);
                    break;
            }
        }

    }
}
