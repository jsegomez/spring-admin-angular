package com.jsegomez.springadmin.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsegomez.springadmin.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long>{
	
	
	// Método para buscar clientes por email
	@Query("Select c from  Cliente c where c.email like %?1%")
	public Cliente findByEmail(String email);
	
	@Query("Select c from  Cliente c where c.nombre like %?1%")
	public List<Cliente> findByName(String name);
	
	@Query("Select c from  Cliente c where c.apellido like %?1%")
	public List<Cliente> findByLastName(String lastName);
}
