package com.mh.prueba.rgaray.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controlador de Inicio.
 *
 * @author rene.garay
 * @version $Revision$ $Date$
 */
@Controller
@RequestMapping(value = {"/", "/mh/"})
public class HomeController extends AbstractWebAppController {

	static String VIEW_HOME = "home";

	@RequestMapping(method = RequestMethod.GET)
	public String defaultPage() {
		return VIEW_HOME;
	}

}
