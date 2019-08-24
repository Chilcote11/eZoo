package com.examples.ezoo.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

/*
 * Two methods created daoLog and controllerLog
 * 		for now they're identical, but may diverge down the road
 * 
 * */

public class ZooLogger {
	
	/*
	 * Log4J Logging Levels:
	 * 			- OFF
	 * 			- FATAL
	 * 			- ERROR
	 * 			- WARN
	 * 			- INFO
	 * 			- DEBUG
	 * 			- ALL
	 * */
	
	private static Logger Log = LogManager.getLogger(ZooLogger.class.getName());
	
	public void daoLog(Origin origin, Level level, Object message) {
		basicLog(origin, level, message);
	}
	
	public void controllerLog(Origin origin, Level level, Object message) {
		basicLog(origin, level, message);
	}
	
	private void basicLog(Origin origin, Level level, Object message) {
		Object mssg = origin + ": " + message;
		Log.log(level, mssg);
	}


}
