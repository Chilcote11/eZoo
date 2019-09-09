package com.examples.ezoo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;


/**
 * Needed for Spring method security
 * 
 * jsr250 means that annotations defined by Java Specification Request 250 
 * will be used to secure methods, namely the `@RolesAllowed` annotation
 * 
 * secureEnabled allows for `@Secured` annotation.. very similar to `@RolesAllowed`
 * 
 * @author Cory Chilcote
 *
 */
@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled=true, securedEnabled=true)	
							// jsr250 and securedEnabled give acess to @RolesAllowed and @Secured respectively
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
	
	/**
	 * Replaces default permission evaluator with one of my own
	 * 
	 * Because the default behavior is to deny every request.
	 * Must also create the CustomPermissionEvaluator... both this and that are in comments for now
	 */
//	@Override
//	protected MethodSecurityExpressionHandler createExpressionHandler() {
//		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//		expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
//		return expressionHandler;
//	}

}
