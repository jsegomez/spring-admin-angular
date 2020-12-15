package com.jsegomez.springadmin.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.jsegomez.springadmin.models.entity.Cliente;
import com.jsegomez.springadmin.services.ClienteService;

@RestController
@RequestMapping(value = "/api/clientes")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ClienteRestController {
	
	@Autowired
	private ClienteService clienteService;
	
	
	// ============================= Método para mostrar todos los clientes =============================  
	@GetMapping(path = "/")
	public ResponseEntity<?> clientes(){
		Map<String, Object> response = new HashMap<>();
		List<Cliente> clientes = null;
		
		try {
			clientes = clienteService.findAll();			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(clientes.isEmpty()) {
			response.put("mensaje", "No se encontraron resultados para la búsqueda");			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", "Consulta realizada con éxito");
		response.put("clientes", clientes);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	// ============================= Método para mostrar cliente por Id =============================
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		Optional<Cliente> cliente = null;
		
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		
		if(cliente.isEmpty()) {
			response.put("mensaje", "Cliente con id: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("cliente", cliente.get());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	// ============================= Método buscar cliente por email =============================
	
	@GetMapping(path = "/email/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email){
		Map<String, Object> response = new HashMap<>();
		List<Cliente> clientes = null;
		
		try {
			clientes = clienteService.findByEmail(email);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		
		if(clientes.isEmpty()) {
			response.put("mensaje", "Cliente con email: " + email + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	
		response.put("clientes", clientes);		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	

	
	// ============================= Método crear un nuevo cliente =============================	
	@PostMapping(path = "/")
	public ResponseEntity<?> save(@RequestBody Cliente cliente){
		Map<String, Object> response = new HashMap<>();
		Cliente newCliente = null;
		
		try {
			newCliente = clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al crear cliente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("cliente", newCliente);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);		
	}	
	
	// ============================= Método actualizar cliente =============================
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Cliente cliente){
		Map<String, Object> response = new HashMap<>();
		Optional<Cliente> clienteDb = null;
		
		try {
			clienteDb = clienteService.findById(id);
			
			if(clienteDb.isEmpty()){
				response.put("mensaje", "Cliente no encontrado en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			Cliente clienteAct = clienteDb.get();
			
			clienteAct.setNombre(cliente.getNombre());
			clienteAct.setApellido(cliente.getApellido());
			clienteAct.setEmail(cliente.getEmail());
			
			clienteService.save(clienteAct);
			
			response.put("cliente", clienteAct);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar cliente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	
	// ============================= Método para eliminar cliente =============================
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();		
	
		try {			
			clienteService.deleteById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar cliente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cliente eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}
	
}
