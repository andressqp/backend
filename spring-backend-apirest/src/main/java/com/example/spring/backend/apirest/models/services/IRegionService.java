package com.example.spring.backend.apirest.models.services;
import java.util.List;
import com.example.spring.backend.apirest.models.entity.Region;

public interface IRegionService {
	
	public List<Region> findAllRegiones();

}
