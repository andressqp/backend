package com.example.spring.backend.apirest.models.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.spring.backend.apirest.models.entity.Region;

public interface IRegionRepository extends CrudRepository<Region,Long>{

}
