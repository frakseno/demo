package com.frakseno.repository;

import com.frakseno.model.Museum;
import org.springframework.data.repository.CrudRepository;

public interface MuseumRepository extends CrudRepository<Museum, Long> {
}
