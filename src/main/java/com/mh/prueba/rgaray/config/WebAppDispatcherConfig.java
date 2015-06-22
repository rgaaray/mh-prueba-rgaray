package com.mh.prueba.rgaray.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author rene.garay
 * @revision $Revision$ $Date$
 */
@Configuration
@EnableWebMvc
@ComponentScan({"com.mh.prueba.rgaray.*"})
public class WebAppDispatcherConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	int cachePeriod = 31556926;
    	
        registry.addResourceHandler("/resources/bootstrap/**").addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/3.3.2/").setCachePeriod(cachePeriod);
        registry.addResourceHandler("/resources/bootstrap-datepicker/**").addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap-datepicker/1.4.0/").setCachePeriod(cachePeriod);
        registry.addResourceHandler("/resources/jquery/**").addResourceLocations("classpath:/META-INF/resources/webjars/jquery/2.1.3/").setCachePeriod(cachePeriod);

        // other resources
        registry.addResourceHandler("/resources/icons/**").addResourceLocations("classpath:/com/famfamfam/silk/").setCachePeriod(cachePeriod);
        registry.addResourceHandler("/resources/css/**").addResourceLocations("/resources/css/").setCachePeriod(cachePeriod);
        registry.addResourceHandler("/resources/img/**").addResourceLocations("/resources/img/").setCachePeriod(cachePeriod);
        registry.addResourceHandler("/resources/js/**").addResourceLocations("/resources/js/").setCachePeriod(cachePeriod);
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
	@Bean
	public ViewResolver viewResolver() {

	    InternalResourceViewResolver publicResolver = new InternalResourceViewResolver();
	    publicResolver.setPrefix("/WEB-INF/views/");
	    publicResolver.setSuffix(".jsp");

	    return publicResolver;
	}
	
    @Override
	public void addInterceptors(InterceptorRegistry registry) {
	}
	    
}