package com.frakseno.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.frakseno.model.Building;
import com.frakseno.model.BuildingType;
import com.frakseno.model.Neighborhood;
import com.frakseno.repository.BuildingRepository;
import com.frakseno.repository.NeighborhoodRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class InitService {
    private static final String NEIGHBORHOOD_FIELD = "neighborhood";
    private static final String NAME_FIELD = "name";
    private static final String ADDRESS_FIELD = "location_1_location";
    private static final String CITY_FIELD = "location_1_city";
    private static final String STATE_FIELD = "location_1_state";
    private static final String ZIP_FIELD = "zipcode";

    @Setter
    private String neiborhoodDataUrl;

    @Setter
    private String museumDataUrl;

    @Setter
    private String restaurantDataUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NeighborhoodRepository neighborhoodRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    public void initializeData() {
        loadNeighborhoodData();
        loadMuseumData();
        loadRestaurantData();
    }

    public void clearData() {
        buildingRepository.deleteAll();
        neighborhoodRepository.deleteAll();
    }

    public boolean isDataInitialized() {
        if (neighborhoodRepository.count() == 0) {
            return false;
        } else if (buildingRepository.count() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void loadNeighborhoodData() {
        List<JsonNode> jsonNodes = loadJsonData(neiborhoodDataUrl);
        List<Neighborhood> neighborhoods = new ArrayList<>();

        for(JsonNode jsonNode : jsonNodes) {
            Neighborhood neighborhood = convertNeighborhood(jsonNode);

            neighborhoods.add(neighborhood);
        }

        neighborhoodRepository.save(neighborhoods);
    }

    private void loadMuseumData() {
        loadBuildingData(BuildingType.MUSEUM);
    }


    private void loadRestaurantData() {
        loadBuildingData(BuildingType.RESTAURANT);
    }

    private void loadBuildingData(BuildingType buildingType) {
        List<JsonNode> jsonNodes = loadJsonData(getBuildingDataUrl(buildingType));
        List<Building> buildings = new ArrayList<>();

        for(JsonNode jsonNode : jsonNodes) {
            Building building = convertBuildingData(jsonNode, buildingType);

            if(building != null) {
                try {
                    buildings.add(building);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        buildingRepository.save(buildings);
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

    private Building convertBuildingData(JsonNode jsonNode, BuildingType buildingType) {
        Building building = new Building();
        building.setBuildingType(buildingType);
        building.setName(jsonNode.path(NAME_FIELD).asText());
        building.setAddress(jsonNode.path(ADDRESS_FIELD).asText());
        building.setCity(jsonNode.path(CITY_FIELD).asText());
        building.setState(jsonNode.path(STATE_FIELD).asText());
        building.setZipCode(jsonNode.path(ZIP_FIELD).asText());

        String neighborhoodName = jsonNode.path(NEIGHBORHOOD_FIELD).asText();
        Neighborhood neighborhood = neighborhoodRepository.findByName(neighborhoodName);

        if(neighborhood == null) {
            building = null;
        } else {
            building.setNeighborhood(neighborhood);
        }

        return building;
    }

    private String getBuildingDataUrl(BuildingType buildingType) {
        if(buildingType == (BuildingType.MUSEUM)) {
            return museumDataUrl;
        } else {
            return restaurantDataUrl;
        }
    }
}
