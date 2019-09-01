package com.examples.ezoo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled=true, securedEnabled=true)	
							// jsr250 and securedEnabled give acess to @RolesAllowed and @Secured respectively
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
	
//	@Override
//	protected MethodSecurityExpressionHandler createExpressionHandler() {
//		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//		expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
//		return expressionHandler;
//	}

}
