package com.frakseno.repository;

import com.frakseno.model.Neighborhood;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ashneyder on 11/13/16.
 */
public interface NeighborhoodRepository extends CrudRepository<Neighborhood, Long> {
    @Cacheable("neighborhoods")
    public Neighborhood findByName(String name);

    @Override
    @CachePut("neighborhoods")
    Neighborhood save(Neighborhood var1);

    @Override
    @CacheEvict("neighborhoods")
    void deleteAll();
}