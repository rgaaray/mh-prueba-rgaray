package com.mh.prueba.rgaray.config;


import java.io.Serializable;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Settings for the DataSource.
 *
 * @author rene.garay
 * @version $Revision$ $Date$
 */
@Configuration
@EnableCaching
@EnableScheduling
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class WebAppDataSourceConfig implements Serializable {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -226775353251965247L;

	@Value(value = "${jdbc.url}")
	private String jdbcUrl;
	
	@Value(value = "${jdbc.driverClassName}")
	private String jdbcDriverClassName;
	
	@Value(value = "${jdbc.username}")
	private String jdbcUsername;
	
	@Value(value = "${jdbc.password}")
	private String jdbcPassword;
	
	@Value(value = "${jdbc.maxActive}")
	private Integer jdbcMaxActive;
	
	@Value(value = "${jdbc.maxIdle}")
	private Integer jdbcMaxIdle;
	
	@Value(value = "${jdbc.maxWait}")
	private Integer jdbcMaxWait;
	
	@Value(value = "${jdbc.defaultAutoCommit}")
	private Boolean jdbcDefaultAutoCommit;
	
	@Value(value = "${jdbc.removeAbandoned}")
	private Boolean jdbcRemoveAbandoned;
	
	@Value(value = "${jdbc.removeAbandonedTimeout}")
	private Integer jdbcRemoveAbandonedTimeout;
	
//	@Value(value = "${jdbc.validationQuery}")
//	private String jdbcValidationQuery;
	
	@Value(value = "${jdbc.testWhileIdle}")
	private Boolean jdbcTestWhileIdle;


	@Bean
    public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(jdbcDriverClassName);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		dataSource.setMaxActive(jdbcMaxActive);
		dataSource.setMaxIdle(jdbcMaxIdle);
		dataSource.setMaxWait(jdbcMaxWait);
		dataSource.setDefaultAutoCommit(jdbcDefaultAutoCommit);
		dataSource.setRemoveAbandoned(jdbcRemoveAbandoned);
		dataSource.setRemoveAbandonedTimeout(jdbcRemoveAbandonedTimeout);
//		dataSource.setValidationQuery(jdbcValidationQuery);
		dataSource.setTestWhileIdle(jdbcTestWhileIdle);

		return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
    	JdbcTemplate jdbcTemplate = new org.springframework.jdbc.core.JdbcTemplate();
    	jdbcTemplate.setDataSource(dataSource());
    	return jdbcTemplate;
    }

    @Bean
    public CacheManager cacheManager()  {
    	return new ConcurrentMapCacheManager();
    }
}

