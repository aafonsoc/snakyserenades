/*
 * Math
 * 
 * 20131121 Alfonso Afonso
 * 
 * Math functions for Snaky Serenades problem
 * 
 */
package com.aafonso.snakyserenades.utils;

import java.util.Random;

public class Math {

	// Random integer generator for a range
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
