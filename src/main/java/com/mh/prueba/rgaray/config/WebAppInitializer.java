package com.mh.prueba.rgaray.config;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author rene.garay
 * @revision $Revision$ $Date$
 */
public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) {
		
		AnnotationConfigWebApplicationContext rootContext =  new AnnotationConfigWebApplicationContext();
		rootContext.register(WebAppConfig.class);
		
		AnnotationConfigWebApplicationContext dispatcherContext =  new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(WebAppDispatcherConfig.class);

		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/mh/*");
		
		container.addListener(new ContextLoaderListener(rootContext));
		
		Dynamic characterEncodingFilter = container.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
		characterEncodingFilter.setInitParameter("encoding", "UTF8");
		characterEncodingFilter.setInitParameter("forceEncoding", "true");
		characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
	}

}
