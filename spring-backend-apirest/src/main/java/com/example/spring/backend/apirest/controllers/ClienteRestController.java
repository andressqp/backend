package com.example.spring.backend.apirest.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.backend.apirest.models.entity.Cliente;
import com.example.spring.backend.apirest.models.services.IClienteService;

@CrossOrigin(origins= {"http://localhost:4200","*"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/Clientes")
	public List<Cliente> verClientes(){
			List<Cliente> clientes = clienteService.findAll();	
			return clientes;

	}
	
	@GetMapping("/Clientes/{id}")
	public Cliente show(@PathVariable int id) {
		return clienteService.findById(id);
	}

	@PostMapping("/Clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@RequestBody Cliente cliente) {
		clienteService.save(cliente);
		return cliente;
	}

	@PutMapping("/Clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente update(@RequestBody Cliente cliente, @PathVariable int id) {
		Cliente currentCliente = clienteService.findById(id);
		currentCliente.setNombre(cliente.getNombre());
		currentCliente.setApellido(cliente.getApellido());
		currentCliente.setIdentificacion(cliente.getIdentificacion());
		clienteService.save(currentCliente);
		return currentCliente;
	}

	@DeleteMapping("/Clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		Cliente currentCliente = clienteService.findById(id);
		clienteService.delete(currentCliente);
	}
}
