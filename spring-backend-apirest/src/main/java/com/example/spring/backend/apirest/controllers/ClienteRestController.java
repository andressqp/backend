package com.example.spring.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.backend.apirest.models.entity.Cliente;
import com.example.spring.backend.apirest.models.services.IClienteService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/Clientes")
	public List<Cliente> verClientes() {
		List<Cliente> clientes = clienteService.findAll();
		return clientes;

	}


	@GetMapping("/Clientes/{id}")
	public ResponseEntity<?> show(@PathVariable int id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();

		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Erro al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (cliente == null) {
			response.put("mensaje",
					"El cliente Id: ".concat(Integer.toString(id).concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@PostMapping("/Clientes")
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {
		Map<String, Object> response = new HashMap<>();

		try {
			clienteService.save(cliente);
			response.put("mensaje", "El cliente a sido creado con exito!!");
			response.put("cliente", cliente);

		} catch (Exception e) {
			response.put("mensaje", "Erro al realizar el insert en la base de datos");
			response.put("error", e.getMessage().toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/Clientes/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		Cliente currentCliente = clienteService.findById(id);
		
		if (currentCliente == null) {
			response.put("mensaje","El cliente Id: ".concat(Integer.toString(id).concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			currentCliente.setNombre(cliente.getNombre());
			currentCliente.setApellido(cliente.getApellido());
			currentCliente.setIdentificacion(cliente.getIdentificacion());
			clienteService.save(currentCliente);
			response.put("mensaje", "El cliente se actualizado con exito!!");
			response.put("cliente", currentCliente);
		} catch (Exception e) {
			response.put("mensaje", "Erro al realizar los cambios en la base de datos");
			response.put("error", e.getMessage().toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@DeleteMapping("/Clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		
		Map<String, Object> response = new HashMap<>();
		Cliente currentCliente = clienteService.findById(id);
		
		if (currentCliente == null) {
			response.put("mensaje","El cliente Id: ".concat(Integer.toString(id).concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		   clienteService.delete(currentCliente);
		   response.put("mensaje", "El cliente se elimino con exito!!");
		   response.put("cliente", currentCliente);
		}catch(Exception e) {
			response.put("mensaje", "Erro al eliminar el cliente de la base de datos");
			response.put("error", e.getMessage().toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
