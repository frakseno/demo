package com.frakseno.repository;

import com.frakseno.model.Neighborhood;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ashneyder on 11/13/16.
 */
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {
    @Cacheable("neighborhoods")
    Neighborhood findByName(String name);
//
//    @Override
//    @CachePut("neighborhoods")
//    public Neighborhood save(Neighborhood neighborhood);
//
//    @Override
//    @CacheEvict("neighborhoods")
//    public void deleteAll();
}