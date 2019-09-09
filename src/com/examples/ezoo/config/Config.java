package com.examples.ezoo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.AnimalDAOImpl;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.EventDAO;
import com.examples.ezoo.dao.EventDAOImpl;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.dao.FeedingScheduleDAOImpl;
import com.examples.ezoo.dao.UserDAO;
import com.examples.ezoo.dao.UserDAOImpl;

/**
 * Dictates to Spring how to manage transactions with Hibernate
 * 
 * @author Cory Chilcote
 *
 */
@Configuration						// from Spring
@EnableTransactionManagement		// specifies that Spring will manage database transactions
public class Config {

	@Bean
	public DataSource dataSource() {									// imported from javax.sql
		DriverManagerDataSource ds = new DriverManagerDataSource();		// imported from spring
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl(DAOUtilities.getURL());
		ds.setUsername(DAOUtilities.getUsername());
		ds.setPassword(DAOUtilities.getPassword());
		return ds;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource ds) {		// imported from spring
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(ds);
		factoryBean.setPackagesToScan(new String[] {"com.examples.ezoo.model"});
		Properties props = new Properties();										// from java.util
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
	
	@Bean FeedingScheduleDAO feedingScheduleDAO (SessionFactory sessionFactory) {
		FeedingScheduleDAOImpl dao = new FeedingScheduleDAOImpl();
		dao.setSessionFactory(sessionFactory);
		return dao;
	}
	
	@Bean 
	UserDAO userDAO (SessionFactory sessionFactory) {
		UserDAOImpl dao = new UserDAOImpl();
		dao.setSessionFactory(sessionFactory);
		return dao;
	}
	
	@Bean
	EventDAO eventDAO(SessionFactory sessionFactory) {
		EventDAOImpl dao = new EventDAOImpl();
		dao.setSessionFactory(sessionFactory);
		return dao;
	}
	
	/**
	 * Configure a PlatformTransactionManager bean to return a HibernateTransactionManager
	 * 
	 * @param sessionFactory
	 * @return
	 */
	@Bean
	public PlatformTransactionManager txManager(SessionFactory sessionFactory) {		// import from Spring
		return new HibernateTransactionManager(sessionFactory);
	}
	
	@Bean
	public BeanPostProcessor persistenceTranslation() {					// from Spring
		return new PersistenceExceptionTranslationPostProcessor();		// from Spring
	}

}
