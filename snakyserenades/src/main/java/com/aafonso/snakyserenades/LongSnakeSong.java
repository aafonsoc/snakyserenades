/*
 * LongSnakeSong
 * 
 * 20131121 Alfonso Afonso
 * 
 * Main class for solving the Snaky Serenades problem
 * 
 * It is a simple main class where we create the controller and let it
 * to solve our problem
 * 
 */
package com.aafonso.snakyserenades;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LongSnakeSong 
{
	// Log 
	protected static Logger log = LoggerFactory.getLogger(LongSnakeSong.class);

	// Controller, the center of the application which control the flow and process 
	private static SnakyController snakyControl;

	// Main method
    public static void main( String[] args )
    {
    	// Check parameters and constructor of Controller class
    	try {
    		snakyControl = new SnakyController(args);
    	} catch (Throwable e) {
			//e.printStackTrace();
    		log.error("Error in "+e.getMessage());
			System.exit(-1);
		}
    	// Launch process
    	try {
			snakyControl.run();
			// Great, show results
			System.out.println("A maximal length "+args[1]+"-MRPS song for this "+snakyControl.getNumSnakes()+
								"-snake ensemble has "+snakyControl.getNumsongs());
			System.out.println(snakyControl.getResult());

			log.info("A maximal length "+args[1]+"-MRPS song for this "+snakyControl.getNumSnakes()+
					"-snake ensemble has "+snakyControl.getNumsongs());
			log.info(snakyControl.getResult());

    	} catch (Throwable e) {
			e.printStackTrace();
		}
    }    
}
