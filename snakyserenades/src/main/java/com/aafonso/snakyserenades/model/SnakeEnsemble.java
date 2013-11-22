/*
 * SnakeEnsemble
 * 
 * 20131121 Alfonso Afonso
 * 
 * Snake Esemble interface
 * 
 * Contract for a storage snake's class
 * 
 * This interface define the basics to store snakes
 * 
 * Provide methods to add snakes to the container, pick-up one (randomly) and pick-up one by name
 * 
 */
package com.aafonso.snakyserenades.model;

public interface SnakeEnsemble {
	
	// Store one snake
	public boolean addSnake(Snake snake);
	// Pick-up one snake randomly
	public Snake pickupSnake();
	// Get one snake by name
	public Snake getSnake(String name);
}
