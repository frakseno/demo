package com.frakseno;

import com.google.maps.GeoApiContext;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ConfigurationProperties(prefix = "com.frakseno")
public class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    @Setter
    private String googleApiKey;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public GeoApiContext geoApiContext() {
        return new GeoApiContext().setApiKey(googleApiKey);
    }

    public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
