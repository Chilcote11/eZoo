package com.examples.ezoo.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.examples.ezoo.dao.DAOUtilities;

public class Config {

	@Bean
	public DataSource dataSource() {									// imported from javax.sql... is this right?
		DriverManagerDataSource ds = new DriverManagerDataSource();		// imported from spring... this is weird
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl(DAOUtilities.getURL());
		ds.setUsername(DAOUtilities.getUsername());
		ds.setPassword(DAOUtilities.getPassword());
		return ds;
	}
	
}
