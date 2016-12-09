package com.frakseno;

import com.frakseno.service.InitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    InitService initService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (!initService.isDataInitialized()) {
            log.info("Initializing the data");

            initService.clearData();

            initService.initializeData();
        }
    }
}