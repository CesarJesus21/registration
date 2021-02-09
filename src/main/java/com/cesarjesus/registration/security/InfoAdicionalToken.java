package com.cesarjesus.registration.security;


import com.cesarjesus.registration.entity.UserEntity;
import com.cesarjesus.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private UserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		Optional<UserEntity> userEntityOptional = userService.findByEmail(authentication.getName());
		Map<String, Object> info = new HashMap<>();

		if(userEntityOptional.isPresent()){
			info.put("idUser", userEntityOptional.get().getId());
			info.put("fullName", userEntityOptional.get().getFirstName().concat(" ").concat(userEntityOptional.get().getLastName()));
			info.put("email", userEntityOptional.get().getEmail());
		}

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
