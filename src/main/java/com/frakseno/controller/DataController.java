package com.frakseno.controller;

import com.frakseno.model.Building;
import com.frakseno.model.Neighborhood;
import com.frakseno.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DataController {
    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/neighborhood", method = RequestMethod.GET)
    public Iterable<Neighborhood> getNeighborhoods() {
        return dataService.getNeighborhoods();
    }

    @RequestMapping(value = "/neighborhood/{id}/restaurant", method = RequestMethod.GET)
    public Iterable<Building> getRestaurantsForNeighborhood(@PathVariable("id") Long id) {
        return dataService.getRestaurantsForNeighborhood(id);
    }

    @RequestMapping(value = "/museum", method = RequestMethod.GET)
    public Iterable<Building> getMuseums() {
        return dataService.getMuseums();
    }

    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET)
    public Building getRestaurantDetails(@PathVariable("id") Long id){
        return dataService.getRestaurantDetails(id);
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.POST)
    public Building saveNewRestaurant(@RequestBody Building restaurant){
        return dataService.saveNewRestaurant(restaurant);
    }
    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.PUT)
    public Building updateRestaurantDetails(@PathVariable("id") Long id, @RequestBody Building restaurant){
        return dataService.updateRestaurant(id, restaurant);
    }
    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.DELETE)
    public void deleteRestaurant(@PathVariable("id") Long id){
        dataService.deleteRestaurant(id);
    }
}