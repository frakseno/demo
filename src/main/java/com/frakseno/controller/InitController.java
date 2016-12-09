package com.frakseno.controller;

import com.frakseno.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {
    @Autowired
    InitService initService;

    @RequestMapping(path = "/init", method = RequestMethod.POST)
    public void initializeData() {
        initService.clearData();

        initService.initializeData();
    }

    @RequestMapping(path = "/init", method = RequestMethod.GET)
    public ResponseEntity checkData() {
        if (initService.isDataInitialized()) {
             return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}