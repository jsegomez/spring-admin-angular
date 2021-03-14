package com.jsegomez.springadmin.services;

import com.jsegomez.springadmin.models.entity.Usuario;

public interface UsuarioServiceInterface {
	
	public Usuario findByUserName(String username);

}
