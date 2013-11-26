package com.aafonso.snakyserenades;

import com.aafonso.snakyserenades.model.SingerSnake;
import com.aafonso.snakyserenades.model.SingerSnakeEnsemble;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple LongSnakeSong.
 */
public class SnakyAppTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SnakyAppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SnakyAppTest.class );
    }

    //-------------------------------------------------------------------
    // Test -------------------------------------------------------------
    //-------------------------------------------------------------------
    
    // Basic SingerSnake test (class)
    public void testSingerSnake(){
    	SingerSnake sn = new SingerSnake();
    	sn.setName("Fon");
    	SingerSnake sn2 = new SingerSnake();
    	sn2.setName("Ra");
    	
    	sn.setHeadEnd(sn2);
    	
    	assertEquals (sn.getHeadEnd().getName(),"Ra");
    	assertEquals (sn.getTailEnd(),null);    	
    }

    // Basic SingerSnakeEnsemble test
    public void testSingerSnakeEsemble(){
 
    	// Snakes
    	SingerSnake sn = new SingerSnake();
    	sn.setName("Fon");    	
    	SingerSnake sn2 = new SingerSnake();
    	sn2.setName("Ra");    	
    	SingerSnake sn3 = new SingerSnake();
    	sn3.setName("XX");
    	
    	// Links
    	sn.setHeadEnd(sn2);
    	sn.setTailEnd(sn3);
    	sn2.setHeadEnd(sn3);

    	// Ensemble and test
    	SingerSnakeEnsemble sne = new SingerSnakeEnsemble();
    	
    	sne.addSnake(sn);
    	sne.addSnake(sn2);
    	sne.addSnake(sn2);
    	sne.addSnake(sn2);
    	sne.addSnake(sn3);
    	
    	SingerSnake sn4 = (SingerSnake) sne.getSnake("Fon");
    	SingerSnake sn5 = (SingerSnake) sne.pickupSnake();
    	SingerSnake sn6 = (SingerSnake) sne.getSnakeAtPos(2);

    	assertEquals(sn4.getName(),"Fon");
    	assertEquals(sn4.getHeadEnd().getName(),"Ra");
    	assertEquals(sn4.getTailEnd().getName(),"XX");
    	
    	assertNotNull(sn5);
       	assertNotNull(sn6);
    }
        
    // App Test (constructor and parameters)
    public void testAppInit()
    {
    	//String[] args = {""};
    	//String[] args = {"samplefiles//sample01.txt","f1"};
    	String[] args = {"snakefiles//sample01.txt","1"};
		SnakyController snac = null;
		
    	try {
			snac = new SnakyController(args);
		} catch (Throwable e) {
			//e.printStackTrace();
		}
		assertEquals(snac.getFileName(),"snakefiles//sample01.txt");
	}
    
    // App Test file sample01 category 0
    public void testAppSample01_0()
    {
    	String[] args = {"snakefiles//sample01.txt","0"};
		SnakyController snac = null;		
    	try {
			snac = new SnakyController(args);
			snac.run();
		} catch (Throwable e) {
		}
		assertEquals(snac.getResultsArray().size(),3);
	}
    
    // App Test file sample01 category 1
    public void testAppSample01_1()
    {
    	String[] args = {"snakefiles//sample01.txt","1"};
		SnakyController snac = null;		
    	try {
			snac = new SnakyController(args);
			snac.run();
		} catch (Throwable e) {
		}
		assertEquals(snac.getResultsArray().size(),3);
	}
    
    // App Test file sample02 category 0
    public void testAppSample02_0()
    {
    	String[] args = {"snakefiles//sample02.txt","0"};
		SnakyController snac = null;		
    	try {
			snac = new SnakyController(args);
			snac.run();
		} catch (Throwable e) {
		}
		assertEquals(snac.getResultsArray().size(),3);
	}
    
    // App Test file sample02 category 1
    public void testAppSample02_1()
    {
    	String[] args = {"snakefiles//sample02.txt","1"};
		SnakyController snac = null;		
    	try {
			snac = new SnakyController(args);
			snac.run();
		} catch (Throwable e) {
		}
		assertEquals(snac.getResultsArray().size(),3);
	}
    
    // App Test file sample04 category 0
    public void testAppSample04_0()
    {
    	String[] args = {"snakefiles//sample04.txt","0"};
		SnakyController snac = null;		
    	try {
			snac = new SnakyController(args);
			snac.run();
		} catch (Throwable e) {
		}
		assertEquals(snac.getResultsArray().size(),10);
	}
    
    // App Test file sample05 category 0
    public void testAppSample05_0()
    {
    	String[] args = {"snakefiles//sample05.txt","0"};
		SnakyController snac = null;		
    	try {
			snac = new SnakyController(args);
			snac.run();
		} catch (Throwable e) {
		}
		assertEquals(snac.getResultsArray().size(),11);
		//"Delta-3, Charlie-2, Echo-4, Foxtrot-5, India-8, Alfa-0, Brava-1, Golf-6, Juliett-9, Kilo-10";
	}
    
}
