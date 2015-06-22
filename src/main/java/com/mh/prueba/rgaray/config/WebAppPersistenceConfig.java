package com.mh.prueba.rgaray.config;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Settings for the JPA persistence.
 *
 * @author rene.garay
 * @version $Revision$ $Date$
 */
@Configuration
@EnableTransactionManagement
@Import({ WebAppDataSourceConfig.class })
@ComponentScan("com.mh.prueba.rgaray.model")
@EnableJpaRepositories("com.mh.prueba.rgaray.model.repository")
public class WebAppPersistenceConfig implements Serializable {

	
	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 6226353043014821948L;

	@Autowired
	private DataSource dataSource;

	@Value("${hibernate.dialect}")
	private String hibernateDialect;
	
	@Value("${hibernate.showSql}")
	private Boolean hibernateShowSql;
	
	@Value("${hibernate.generateDdl}")
	private Boolean hibernateGenerateDdl;
	
	@Value("${jpa.packagesToScan}")
	private String packagesToScan;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPersistenceUnitName("mhWebAppPU");
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		entityManagerFactoryBean.setJpaDialect(jpaDialect());
		entityManagerFactoryBean.setPackagesToScan(packagesToScan);
		return entityManagerFactoryBean;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter h = new HibernateJpaVendorAdapter();
		h.setDatabase(Database.POSTGRESQL);
		h.setDatabasePlatform(hibernateDialect);
		h.setGenerateDdl(hibernateGenerateDdl);
		h.setShowSql(hibernateShowSql);
		return h;
	}

	@Bean
	public JpaDialect jpaDialect() {
		return new HibernateJpaDialect();
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

}
