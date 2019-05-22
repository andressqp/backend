package com.example.spring.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.backend.apirest.models.entity.Region;
import com.example.spring.backend.apirest.models.repository.IRegionRepository;
@Service
public class RegionServiceImpl implements IRegionService{
	
	@Autowired
	private IRegionRepository regionrepo;
	

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegiones() {
		return (List<Region>) regionrepo.findAll();
		
	}

}
