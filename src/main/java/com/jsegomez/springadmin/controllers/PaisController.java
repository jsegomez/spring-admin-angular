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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsegomez.springadmin.models.entity.Pais;
import com.jsegomez.springadmin.services.PaisService;

@RestController
@RequestMapping(value = "/api/paises")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PaisController {
	
	@Autowired
	private PaisService paisService;
		
	// Método para mostrar todos los paises
	@GetMapping(path = "/lista")
	public ResponseEntity<?> findAll(){
		Map<String, Object> response = new HashMap<>();
		List<Pais> paises = null;
		
		try {
			paises = paisService.findAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(paises.isEmpty()) {
			response.put("Mensaje", "No se encontraron resultados para la búsqueda");
			response.put("error", "Sin datos para mostrar");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);			
		}
		
		response.put("paises", paises);
		response.put("mensaje", "Consulta realizada con éxito");		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	// Método para mostrar país por ID
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		Optional<Pais> pais = null;
		
		try {
			pais = paisService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		if(pais.isEmpty()) {
			response.put("Mensaje", "No se encontraron resultados para la búsqueda");
			response.put("error", "Sin datos para mostrar");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);			
		}
		
		response.put("pais", pais.get());
		response.put("mensaje", "Consulta realizada con éxito");		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	// Método para crear un nuevo país
	@PostMapping(path = "/")
	public ResponseEntity<?> save(@RequestBody Pais pais){
		Map<String, Object> response = new HashMap<>();
		Pais paisNew = null;
		
		try {
			paisNew = paisService.save(pais);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al crear país");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		response.put("pais", paisNew);
		response.put("mensaje", "País creado con éxito");		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	// Método para crear un nuevo país
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> update(@RequestBody Pais pais, @PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		Optional<Pais> findPais = null;
		Pais paisUpdate = null;
		
		try {
			findPais = paisService.findById(id);
			
			if(findPais.isEmpty()) {
				response.put("mensaje", "País con id ".concat(id.toString()).concat(" no existe en la base de datos"));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			paisUpdate = findPais.get();			
			paisUpdate.setNombre(pais.getNombre());
			paisService.save(paisUpdate);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar país");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		response.put("pais", paisUpdate);
		response.put("mensaje", "País actualizado con éxito");		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
