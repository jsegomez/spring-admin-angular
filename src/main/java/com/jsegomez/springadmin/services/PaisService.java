package com.jsegomez.springadmin.services;

import java.util.List;
import java.util.Optional;

import com.jsegomez.springadmin.models.entity.Pais;

public interface PaisService {
	
	// Método para mostrar todos los paises
	public List<Pais> findAll();
	
	// Buscar país por su id de país
	public Optional<Pais> findById(Long id);
	
	// Método para crear un nuevo país
	public Pais save(Pais pais);
	
	// Método para eliminar un país
	public void delete(Long id);

}
