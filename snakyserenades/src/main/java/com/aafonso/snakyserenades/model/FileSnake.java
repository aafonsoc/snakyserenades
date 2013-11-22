/*
 * FileSnake
 * 
 * 20131121 Alfonso Afonso
 * 
 * FileSnake Class used to store the information from the file in the same format it comes
 * 
 * As we have links between snakes that may not be available in our final structure, 
 * we store the representation of the file and the save the order on which they come, 
 * so then we can link the final data queue properly
 * 
 */package com.aafonso.snakyserenades.model;

 public class FileSnake extends Snake {

	 private int head;
	 private int tail;

	 /*
	  * Getter & Setter section 
	  */

	 public int getHead() {
		 return head;
	 }
	 public void setHead(int head) {
		 this.head = head;
	 }
	 public int getTail() {
		 return tail;
	 }
	 public void setTail(int tail) {
		 this.tail = tail;
	 } 
	 // ToString
	 public String toString(){
		 return getName() + " " + getHead() + " " + getTail();
	}

 }



