package com.jsegomez.springadmin.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jsegomez.springadmin.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	@Query("select u from Usuario u where u.username=?1")
	public Usuario buscarUsuario(String username);	
	
}
