/*
 * Conversion and managing data utils
 * 
 * 20131121 Alfonso Afonso
 * 
 * Functions to manage data and convert from plain text to object
 * 
 */
package com.aafonso.snakyserenades.utils;

import com.aafonso.snakyserenades.model.FileSnake;

public class Conversion {
	
	// Converts from a line of text with comma separated value to an FileSnake object
	public static FileSnake decodeLine(String line){
		int pos,fin;
		String name = "",tail="-1",head="-1";

		// Divide the line into the three parameters, name, tail and head
		// If one of them does not appear, we fill the data automatically with correct values
		pos=0; fin = line.indexOf(',');
		if (fin == -1) {
			fin=line.length();
			name = line.substring(pos,fin).trim();
		}
		else {		
			name = line.substring(pos,fin).trim();
			pos=fin+1; fin = line.indexOf(',',pos);
			if (fin != -1){ 
				tail = line.substring(pos,fin).trim();
				pos=fin+1; fin = line.length();
				if (fin != -1) head = line.substring(pos,fin).trim();
				else head = "-1";
			}
			else {
				fin=line.length();
				head = line.substring(pos,fin).trim();
			}
		}
		// Create object and return
		FileSnake fs = new FileSnake();
		fs.setTail(Integer.valueOf(tail));
		fs.setHead(Integer.valueOf(head));
		fs.setName(name);
		
		return fs;
	}
}
