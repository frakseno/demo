package com.frakseno.service;

/**
 * Created by ashneyder on 11/13/16.
 */
public interface InitService {
    public void loadNeighborhoodData();

    public void loadMuseumData();

    public void loadRestaurantData();

    public void clearData();

    public boolean isDataInitialized();
}
