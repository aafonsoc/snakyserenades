/*
 * SingerSnakeEnsemble
 * 
 * 20131121 Alfonso Afonso
 * 
 * SingerSnakeEnsemble Class
 * 
 * Storage snake's class implementing the SnakeEnsemble interface
 * 
 * This class store a LinkedHashMap with different SingerSnakes.
 * 
 * It's used a Linked instead of basic Hash to allow linear read of data
 * 
 * Provide methods to add snakes to the container, pick-up one (randomly) and pick-up one by name
 * 
 */
package com.aafonso.snakyserenades.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.aafonso.snakyserenades.utils.Math;

public class SingerSnakeEnsemble implements SnakeEnsemble{
	Map<String, SingerSnake> SingerSnakesMap;
	
	public SingerSnakeEnsemble(){
		SingerSnakesMap = new LinkedHashMap<String,SingerSnake>();
	}
	
	// Add a Snake to the LinkedHasMap
	@Override
	public boolean addSnake(Snake snake) {
		if (SingerSnakesMap.put(snake.getName(), (SingerSnake) snake) != null) return true;
		else return false;
	}

	// Return a randomly SingerSnake
	@Override
	public Snake pickupSnake() {
		int pos = Math.randInt(0, SingerSnakesMap.size()-1);		
		SingerSnake res = (new ArrayList<SingerSnake>(SingerSnakesMap.values())).get(pos);
		return res;
	}

	// Return the SingerSnake searching by key (name)
	@Override
	public Snake getSnake(String name) {
		SingerSnake res = SingerSnakesMap.get(name);
		return res;
	}
	
	public Snake getSnakeAtPos(int pos){
		if (pos > SingerSnakesMap.size()-1) return null;
		else return (new ArrayList<SingerSnake>(SingerSnakesMap.values())).get(pos);		
	}
	
	public int size(){
		return SingerSnakesMap.size();
	}

}
