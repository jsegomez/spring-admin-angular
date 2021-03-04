package com.jsegomez.springadmin.controllers;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsegomez.springadmin.models.entity.Cliente;
import com.jsegomez.springadmin.services.ClienteService;
import com.jsegomez.springadmin.services.UploadImage;

@RestController
@RequestMapping(value = "/api/image")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UploadController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private UploadImage uploadService;
	
	private final Logger log = LoggerFactory.getLogger(UploadController.class);
	
	// Método para cargar imagenes
	@PostMapping(path = "/upload")
	public ResponseEntity<?> upload(@RequestParam MultipartFile file, @RequestParam Long id){
		Map<String, Object> response = new HashMap<>();
		Cliente cliente = clienteService.findById(id).get();
		String fileName = null;
		
		if(!file.isEmpty()) {
			try {
				if(cliente.getImagen() != null) {
					uploadService.delete(cliente.getImagen());
				}
				
				fileName = uploadService.upload(file);
				cliente.setImagen(fileName);
				clienteService.save(cliente);
			} catch (Exception e) {
				response.put("mensaje", "Error al cargar imagen");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_GATEWAY);
			}
		}
		
		response.put("mensaje", "Archivo cargado con éxito");
		response.put("cliente", cliente);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	// Método para mostrar imagen
	@GetMapping(path = "/{image:.+}")
	public ResponseEntity<Resource> getImage(@PathVariable String image){
		Resource resource = null;
		
		try {
			resource = uploadService.showImg(image);						
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		log.info(resource.toString());
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+"\"");
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	
	// Método para eliminar imagenes
	@PutMapping("/delete/{id}")
	public ResponseEntity<?> deleteImg(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		Cliente clienteDb = clienteService.findById(id).get();
		
		if(clienteDb == null) {
			response.put("mensaje", "Cliente no existe en la base de datos");
			response.put("error", "Cliente no encontrado en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			uploadService.delete(clienteDb.getImagen());
			clienteDb.setImagen("");
			clienteService.save(clienteDb);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar imagen");
			response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Imagen eliminada con éxito");
		response.put("cliente", clienteDb);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
