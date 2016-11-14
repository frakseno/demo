package com.frakseno.service;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * Created by ashneyder on 11/14/16.
 */
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "com.frakseno")
@Service("locationService")
public class LocationServiceImpl implements LocationService{
    @Setter
    private String googleApiKey;
}
