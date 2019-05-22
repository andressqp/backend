package com.example.spring.backend.apirest.models.repository;

//import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.spring.backend.apirest.models.entity.Cliente;
//import com.example.spring.backend.apirest.models.entity.Region;

public interface IClienteRepository extends CrudRepository<Cliente,Integer> {
	
	//@Query("from Region")
	//public List<Region> findAllRegiones();
	
}
