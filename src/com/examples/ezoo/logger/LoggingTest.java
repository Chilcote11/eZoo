package com.examples.ezoo.logger;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LoggingTest {
	
	private static Logger Log = Logger.getLogger(LoggingTest.class.getName());

	public static void main(String[] args) {
		
		// The default logging level is DUBUG - this should be printed
		Log.debug("Debug message");
		
		// Raise the logging level to FATAL
		Log.setLevel(Level.FATAL);
		
		// The new logging level is FATAL - This message should be skipped
		Log.info("Info message.. should not be printed");
		
		// This FATAL message should be printed
		Log.fatal("Oh no! This is a fatal message!");

	}

}
