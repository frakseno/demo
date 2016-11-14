package com.frakseno.controller;

import com.frakseno.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ashneyder on 11/13/16.
 */
@RestController("/init")
public class InitController {
    @Autowired
    InitService initService;

    @RequestMapping(method = RequestMethod.POST)
    public void initializeData() {
        initService.clearData();
        initService.loadNeighborhoodData();
        initService.loadMuseumData();
        initService.loadRestaurantData();

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity checkData() {
        if (initService.isDataInitialized()) {
             return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void resetData() {
        initService.clearData();
    }
}