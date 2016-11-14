package com.frakseno.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.frakseno.model.Museum;
import com.frakseno.model.Neighborhood;
import com.frakseno.model.Restaurant;
import com.frakseno.repository.MuseumRepository;
import com.frakseno.repository.NeighborhoodRepository;
import com.frakseno.repository.RestaurantRepository;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@EnableConfigurationProperties
@ConfigurationProperties(prefix = "com.frakseno")
@Service("initService")
@Transactional
public class InitServiceImpl implements InitService {
    private static final Logger log = LoggerFactory.getLogger(InitServiceImpl.class);


    private static final String NEIGHBORHOOD_FIELD = "neighborhood";
    private static final String NAME_FIELD = "name";
    private static final String ADDRESS_FIELD = "location_1_location";
    private static final String CITY_FIELD = "location_1_city";
    private static final String STATE_FIELD = "location_1_state";
    private static final String ZIP_FIELD = "zipcode";

    @Setter
    private String neibothoodDataUrl;

    @Setter
    private String museumDataUrl;

    @Setter
    private String restaurantDataUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NeighborhoodRepository neighborhoodRepository;

    @Autowired
    private MuseumRepository museumRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public void loadNeighborhoodData() {
        List<JsonNode> jsonNodes = loadJsonData(neibothoodDataUrl);
        List<Neighborhood> neighborhoods = new ArrayList<>();

        for(JsonNode jsonNode : jsonNodes) {
            neighborhoods.add(convertNeighborhood(jsonNode));
        }

        neighborhoodRepository.save(neighborhoods);
    }

    @Override
    public void loadMuseumData() {
        List<JsonNode> jsonNodes = loadJsonData(museumDataUrl);
        List<Museum> museums = new ArrayList<>();

        for(JsonNode jsonNode : jsonNodes) {
            Museum museum = convertMuseumData(jsonNode);

            if(museum != null) {
                museums.add(museum);
            }
        }

        museumRepository.save(museums);
    }

    @Override
    public void loadRestaurantData() {
        List<JsonNode> jsonNodes = loadJsonData(restaurantDataUrl);
        List<Restaurant> restaurants = new ArrayList<>();

        for(JsonNode jsonNode : jsonNodes) {
            Restaurant restaurant = convertRestaurantData(jsonNode);

            if(restaurant != null) {
                restaurants.add(restaurant);
            }
        }

        restaurantRepository.save(restaurants);
    }

    @Override
    public void clearData() {
        neighborhoodRepository.deleteAll();
        museumRepository.deleteAll();
        restaurantRepository.deleteAll();
    }

    @Override
    public boolean isDataInitialized() {
        if (neighborhoodRepository.count() == 0) {
            return false;
        } else if (museumRepository.count() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private List<JsonNode> loadJsonData(String dataUrl) {
        ResponseEntity<List<JsonNode>> responseEntity =
                restTemplate.exchange(dataUrl,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<JsonNode>>() {
                        });

        return responseEntity.getBody();
    }

    private Neighborhood convertNeighborhood(JsonNode jsonNode) {
        Neighborhood neighborhood = new Neighborhood();
        neighborhood.setName(jsonNode.path(NEIGHBORHOOD_FIELD).asText());

        return neighborhood;
    }

    private Museum convertMuseumData(JsonNode jsonNode) {
        Museum museum = new Museum();
        museum.setName(jsonNode.path(NAME_FIELD).asText());
        museum.setAddress(jsonNode.path(ADDRESS_FIELD).asText());
        museum.setCity(jsonNode.path(CITY_FIELD).asText());
        museum.setState(jsonNode.path(STATE_FIELD).asText());
        museum.setZipCode(jsonNode.path(ZIP_FIELD).asText());

        String neighborhoodName = jsonNode.path(NEIGHBORHOOD_FIELD).asText();
        Neighborhood neighborhood = neighborhoodRepository.findByName(neighborhoodName);

        if(neighborhood == null) {
            museum = null;
        } else {
            museum.setNeighborhood(neighborhood);
        }

        return museum;
    }

    private Restaurant convertRestaurantData(JsonNode jsonNode) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(jsonNode.path(NAME_FIELD).asText());
        restaurant.setAddress(jsonNode.path(ADDRESS_FIELD).asText());
        restaurant.setCity(jsonNode.path(CITY_FIELD).asText());
        restaurant.setState(jsonNode.path(STATE_FIELD).asText());
        restaurant.setZipCode(jsonNode.path(ZIP_FIELD).asText());

        String neighborhoodName = jsonNode.path(NEIGHBORHOOD_FIELD).asText();
        Neighborhood neighborhood = neighborhoodRepository.findByName(neighborhoodName);

        if(neighborhood == null) {
            restaurant = null;
        } else {
            restaurant.setNeighborhood(neighborhood);
        }

        return restaurant;
    }
}
