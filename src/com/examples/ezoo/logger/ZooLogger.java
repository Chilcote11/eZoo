package com.examples.ezoo.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * Logger for the entire eZoo application
 * 
 * Uses a custom implementation of Log4J
 * Configuration found in log4j2.properties
 * 	log messages saved to file with location found there
 * 
 * Two methods created: daoLog and controllerLog.
 * 		For now they're identical, but may diverge down the road
 * 
 * @author Cory Chilcote
 * @since 2.0
 * */

public class ZooLogger {
	
	/*
	 * Log4J Logging Levels:
	 * 		- OFF
	 * 		- FATAL
	 * 		- ERROR
	 * 			- controller exceptions
	 * 		- WARN
	 * 			- controller validation errors
	 * 		- INFO
	 * 			- controller navigation
	 * 		- DEBUG
	 * 			- all DAO logging
	 * 		- TRACE
	 * 		- ALL
	 * */
	
	private static Logger Log = LogManager.getLogger(ZooLogger.class.getName());
	
	public void daoLog(Origin origin, Level level, Object message) {
		basicLog(origin, level, message);
	}
	
	public void controllerLog(Origin origin, Level level, Object message) {
		basicLog(origin, level, message);
	}
	
	private void basicLog(Origin origin, Level level, Object message) {
		String mssg = String.format("Level: %-5s - %-28s: %s", level, origin, message);
		Log.log(level, mssg);
	}


}
