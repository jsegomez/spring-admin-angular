package com.jsegomez.springadmin.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.jsegomez.springadmin.models.dao.IUsuarioDao;
import com.jsegomez.springadmin.models.entity.Usuario;

@Service
public class UsuarioService implements UserDetailsService, UsuarioServiceInterface{
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.buscarUsuario(username);
		
		if(usuario == null) {
			logger.error("No existe usuario en el sistema");
			throw new UsernameNotFoundException("No existe usuario en el sistema");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream().map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.collect(Collectors.toList());
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUserName(String username) {
		return usuarioDao.buscarUsuario(username);
	}

}
