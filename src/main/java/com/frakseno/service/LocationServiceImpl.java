package com.frakseno.service;

import com.frakseno.model.Building;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("locationService")
public class LocationServiceImpl implements LocationService {
    @Autowired
    GeoApiContext geoApiContext;


    public void verifyBuildingLocation(Building building) throws Exception {
        GeocodingResult result =  GeocodingApi.geocode(geoApiContext, building.getFullAddress()).await()[0];

        building.setLatitude(result.geometry.location.lat);
        building.setLongitude(result.geometry.location.lng);

        for(AddressComponent addressComponent : result.addressComponents) {
            AddressComponentType addressComponentType = addressComponent.types[0];
            switch (addressComponentType) {
                case STREET_NUMBER:
                    building.setStreetNumber(addressComponent.longName);
                    break;
                case ROUTE:
                    building.setStreetName(addressComponent.longName);
                    break;
                case LOCALITY:
                    building.setCity(addressComponent.longName);
                    break;
                case ADMINISTRATIVE_AREA_LEVEL_1:
                    building.setState(addressComponent.shortName);
                    break;
                case POSTAL_CODE:
                    building.setZipCode(addressComponent.shortName);
                    break;
            }
        }
    }
}
