package com.jsegomez.springadmin.services;

import java.util.List;
import java.util.Optional;

import com.jsegomez.springadmin.models.entity.Cliente;

public interface ClienteService {
	
	// Buscar todos los clientes	
	public List<Cliente> findAll();
	
	// Buscar cliente por Id
	public Optional<Cliente> findById(Long id);
	
	// Método para crear un nuevo cliente
	public Cliente save(Cliente cliente);
	
	// Método para eliminar un cliente
	public void deleteById(Long id);	
	
	// Método para buscar cliente por email 
	public List<Cliente> findByEmail(String email);
	
	// Método para buscar cliente por nombre
	public List<Cliente> findByName(String name);
	
	// Método para buscar cliente por apellido
	public List<Cliente> findByLastName(String lastName);

}
