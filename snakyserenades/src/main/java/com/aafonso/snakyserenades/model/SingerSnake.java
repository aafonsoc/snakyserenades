/*
 * SingerSnake
 * 
 * 20131121 Alfonso Afonso
 * 
 * Singer Snake Class
 * 
 * Extending basic abstract Snake, we add here the Head and Tail linked snakes (if any)
 * 
 * Also we provide a singChance value that represents the percentage to start to sing when
 * is called
 * 
 */
package com.aafonso.snakyserenades.model;

final public class SingerSnake extends Snake{
	private Snake headEnd;
	private Snake tailEnd;
	private int singChance;
		
	// Method to guess if the Snake is going to sing or not
	public boolean sing(int chanceValue){
		if (chanceValue>=getSingChance()) return true;
		else return false;
	}

	/*
	 * Getter & Setter section 
	 */
	
	public Snake getHeadEnd() {
		return headEnd;
	}
	public void setHeadEnd(Snake headEnd) {
		this.headEnd = headEnd;
	}
	public Snake getTailEnd() {
		return tailEnd;
	}
	public void setTailEnd(Snake tailEnd) {
		this.tailEnd = tailEnd;
	}
	public int getSingChance() {
		return singChance;
	}
	public void setSingChance(int singChance) {
		this.singChance = singChance;
	}
	// ToString
	public String toString(){
		String head = "",tail="";
		if (getHeadEnd()!=null) head=getHeadEnd().getName();
		if (getTailEnd()!=null) tail=getTailEnd().getName();
		return getName() + " " + head + " " + tail;
	}

}
