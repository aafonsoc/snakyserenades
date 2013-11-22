/*
 * SimpleFile
 * 
 * 20131121 Alfonso Afonso
 * 
 * Simple file class
 * 
 */
package com.aafonso.snakyserenades.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SimpleFile {
	
	private String filename;
	BufferedReader br;
	
	public SimpleFile(String fileName){
		this.filename=fileName;
	}
	
	public void openFile() throws FileNotFoundException{
			br = new BufferedReader(new FileReader(filename));
	}
	
	public String readFile() throws IOException{
		return br.readLine();
	}

	public void closeFile() throws IOException{
		br.close();
	}
}
