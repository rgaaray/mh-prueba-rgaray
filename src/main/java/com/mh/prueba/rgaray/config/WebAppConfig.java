package com.mh.prueba.rgaray.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Base application configuration.
 * 
 * @author rene.garay
 * @revision $Revision$ $Date$
 */

@EnableWebMvc
@EnableAspectJAutoProxy
@Import({ WebAppPersistenceConfig.class, WebAppSecurityConfig.class })
@ComponentScan({"com.mh.prueba.rgaray.*"})
@Configuration
public class WebAppConfig {
	
    @Bean
    public static PropertyPlaceholderConfigurer properties() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        Resource[] resource = new ClassPathResource[] { new ClassPathResource("/mh-prueba-rgaray.properties") };
        ppc.setLocations(resource);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }
	
}
