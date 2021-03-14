package com.jsegomez.springadmin.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.jsegomez.springadmin.models.entity.Usuario;
import com.jsegomez.springadmin.services.UsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> agregarInfo = new HashMap<>();		
		Usuario usuario = usuarioService.findByUserName(authentication.getName());
		
		agregarInfo.put("nombre_usuario", usuario.getUsername());
		agregarInfo.put("nombre", usuario.getNombre());
		agregarInfo.put("apellido", usuario.getApellido());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(agregarInfo);
		
		return accessToken;
	}

}
