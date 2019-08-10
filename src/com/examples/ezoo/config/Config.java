package com.examples.ezoo.config;

import java.util.Properties;

import javax.jms.Session;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.AnimalDAOImpl;
import com.examples.ezoo.dao.DAOUtilities;

public class Config {

	@Bean
	public DataSource dataSource() {									// imported from javax.sql... is this right? - video says yes
		DriverManagerDataSource ds = new DriverManagerDataSource();		// imported from spring... this is weird
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl(DAOUtilities.getURL());
		ds.setUsername(DAOUtilities.getUsername());
		ds.setPassword(DAOUtilities.getPassword());
		return ds;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource ds) {		// imported from spring, this should be right
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(ds);
		factoryBean.setPackagesToScan(new String[] {"com.examples.ezoo.model"});	// may need more?
		Properties props = new Properties();										// from java.util.. is this right?
		props.setProperty("dialect", "org.hibernate.dialect.PostgreSQL95Dialect");	// newest dialect for PostgreSQL I could find
		factoryBean.setHibernateProperties(props);
		return factoryBean;
	}
	
	@Bean
	public AnimalDAO animalDAO (SessionFactory sessionFactory) {
		AnimalDAOImpl dao = new AnimalDAOImpl();
		dao.setSessionFactory(sessionFactory);
		return dao;
	}
	
	@Bean
	public PlatformTransactionManager txManager(SessionFactory sessionFactory) {		// import from Spring
		return new HibernateTransactionManager(sessionFactory);
	}
	
}
