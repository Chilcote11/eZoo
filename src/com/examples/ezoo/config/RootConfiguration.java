package com.examples.ezoo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.FilterType;		// had to manually look up and import this.. strange

@Configuration
@ComponentScan( basePackages = { "com.examples.ezoo" }, 
		excludeFilters = { @Filter(type=FilterType.ANNOTATION, value = EnableWebMvc.class) } )
public class RootConfiguration {

}
