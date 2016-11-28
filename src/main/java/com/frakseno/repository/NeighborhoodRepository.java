package com.frakseno.repository;

import com.frakseno.model.Neighborhood;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
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