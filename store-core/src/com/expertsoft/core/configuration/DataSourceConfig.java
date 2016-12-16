package com.expertsoft.core.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.expertsoft.core.model.JdbcProductDao;

@Configuration
@ComponentScan(basePackageClasses=JdbcProductDao.class)
public class DataSourceConfig {
	 
	private Environment env;
	
	@Autowired
	public DataSourceConfig(Environment env) {
		this.env = env;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getProperty("db.driver"));
		ds.setUrl(env.getProperty("db.url"));
		ds.setUsername(env.getProperty("db.user"));
		ds.setPassword(env.getProperty("db.password"));
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		
		if (env.getProperty("db.insertSchema", Boolean.class)) {
			populator.addScript(new ClassPathResource("com/expertsoft/core/sql/schema.sql"));
		}
		if (env.getProperty("db.insertData", Boolean.class)) {
			populator.addScript(new ClassPathResource("com/expertsoft/core/sql/test-data.sql"));
		}
		
		return populator;
	}
	
	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource ds, DatabasePopulator populator) {
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(ds);
		initializer.setDatabasePopulator(populator);
		return initializer;
	}

}
