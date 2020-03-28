package com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.auth;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity.EndUser;
import com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.services.IUserService;


@Component
public class AditionalInfoToken implements TokenEnhancer{
	
	@Autowired
	private IUserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		EndUser user = this.userService.findByUsername(authentication.getName());

		Map<String, Object> info = new HashMap<>();
		info.put("info_adicional", "Hola que tal!: ".concat(authentication.getName()));
		
		info.put("nombre", user.getFirstName());
		info.put("apellido", user.getLastName());
		info.put("email", user.getEmail());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
