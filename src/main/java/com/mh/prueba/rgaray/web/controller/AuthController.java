package com.mh.prueba.rgaray.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador de Inicio.
 *
 * @author rene.garay
 * @version $Revision$ $Date$
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthController extends AbstractWebAppController {

	public static String VIEW_LOGIN = "login";

	public static String VIEW_LOGOUT = "logout";

	/**
	 * ACCION : Mostrar vista de login.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Credenciales Invalidas");
		}

		model.setViewName(VIEW_LOGIN);

		return model;

	}

	/**
	 * ACCION : Mostrar vista de logout.
	 */
	//@RequestMapping(value = "/logout" , method = RequestMethod.POST)
	public String logout() {
		return VIEW_LOGOUT;
	}

}
