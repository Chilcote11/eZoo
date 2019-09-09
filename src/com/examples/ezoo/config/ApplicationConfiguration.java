package com.examples.ezoo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Provides configuration for Spring MVC container
 * 
 * @author Cory Chilcote
 * @since 2.0
 *
 */
public class ApplicationConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * Informs Servlet container which URL patterns the DispatcherServlet is mapped to
	 * In this case I map to root, so it will handle all incoming requests for the application
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	/**
	 * The two methods below inform Spring of configuration classes and effectively link them
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfiguration.class, Config.class, WebSecurityConfiguration.class};
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {ServletConfiguration.class };
	}	
}
