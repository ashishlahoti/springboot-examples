package com.abc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.abc.service.CryptoService;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class PersistenceConfig {

	@Autowired
	private CryptoService cryptoService;
	
	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConfigurationProperties("spring.datasource.hikari")
	public HikariDataSource dataSource(DataSourceProperties properties) {
		properties.setPassword(cryptoService.decrypt(properties.getPassword()));
		return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
}