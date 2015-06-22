package com.mh.prueba.rgaray.config.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.mh.prueba.rgaray.model.service.WebAppServiceException;
import com.mh.prueba.rgaray.model.service.sec.SecUserService;


/**
 * Proveedor de la autenticación personalizada de Gestor.
 *
 * @author rene.garay
 * @version 13/01/2015
 *
 */
@Component("authenticationProvider")
public class WebAppDaoAuthenticationProvider extends DaoAuthenticationProvider {
	
	@Autowired
	SecUserService secUserService;

	/**
	 * Static Logger.
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(WebAppDaoAuthenticationProvider.class);

	@Autowired
	@Qualifier("userDetailsService")
	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		Authentication auth = null;

		try {
			Integer exist = secUserService.existUser(authentication.getName());
			if (exist < 1)
				throw new WebAppServiceException("");
		} catch (WebAppServiceException e) {
			LOGGER.error(e.getMessage());
			throw new BadCredentialsException("Credenciales Invalidas");
		}
		
		auth = super.authenticate(authentication);

		return auth;

	}
	
}