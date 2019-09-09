package com.examples.ezoo.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * Very simple test script for verifying Log4J setup
 * 
 * Read from log file specified in log4j2.properties to verify setup
 * 
 * @author Cory Chilcote
 *
 */
public class LoggingTest {
	
	private static Logger Log = LogManager.getLogger(LoggingTest.class.getName());

	public static void main(String[] args) {
		
		// The default logging level is DUBUG - this should be printed
		Log.debug("Debug message");
		
		// Raise the logging level to FATAL
//		Log.setLevel(Level.FATAL);															// no setLevel method for this Logger
//		Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.FATAL);		// changes level universally
		Configurator.setLevel(Log.getName(), Level.FATAL);									// changes level for current class
		
		// The new logging level is FATAL - This message should be skipped
		Log.info("Info message.. should not be printed");
		
		// This FATAL message should be printed
		Log.fatal("Oh no! This is a fatal message!");

	}

}
