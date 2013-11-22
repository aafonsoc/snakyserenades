/*
 * SnakyController
 * 
 * 20131121 Alfonso Afonso
 * 
 * Basic class to control all the process and structures to solve the Snaky Serenades Problem
 * First of all, we check the parameters, file and other stuff
 * Then, we create the classes to store our bunch of singers snakes
 * Finally, we process one song of the request level
 * 
 */
package com.aafonso.snakyserenades;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aafonso.snakyserenades.exeptions.SnakyException;
import com.aafonso.snakyserenades.model.FileSnake;
import com.aafonso.snakyserenades.model.SingerSnake;
import com.aafonso.snakyserenades.model.SingerSnakeEnsemble;
import com.aafonso.snakyserenades.model.SongsResults;
import com.aafonso.snakyserenades.model.SortArraySongsResults;
import com.aafonso.snakyserenades.utils.SimpleFile;
import com.aafonso.snakyserenades.utils.Conversion;

public class SnakyController {

	// Log 
	protected Logger log = LoggerFactory.getLogger(SnakyController.class);
	// Basics, file and request song level
	private String fileName;
	private int songCategory;
	// Work objects
	private static SingerSnakeEnsemble ensemble = new SingerSnakeEnsemble();
	private static SerenadeComposer serenade;

	// Constructor
	public SnakyController( String[] args ) throws Throwable
	{
		// Auto check parameters directly in constructor, so the programmer can't build the
		// object without the proper configuration
		try{
			checkParams(args);
		} catch(Exception e){ 
			throw e;
		}
	}

	// Parameter checker
	private void checkParams( String[] args) throws Throwable{
		// Number of parameters
		try{
			if (args.length != 2) {
				// We accept only 2 parameters, whatever they are one or one hundred, we can't manage 'em
				log.error("Error in number of parameter, aborting execution");
				System.out.println("Error in parameter list!!");
				System.out.println("Use syntax: java -jar snakyserenades-0.0.1.jar <file_to_process> <song_category>");
				throw new SnakyException("Error in number of parameters. Aborting execution.");
			}
			// Log
			log.debug("Number of Parameters checked");
			
			fileName = args[0];
			songCategory = Integer.valueOf(args[1]);
			//Log
			log.info("Parameters read: Filename='"+fileName+"' and Category='"+songCategory+"'");
			
			// Check file exist
			File f = new File(fileName);
			if(!f.exists()){
				log.error("SimpleFile not exists - '"+fileName+"'");
				System.out.println("Error in File!!");
				System.out.println("File description can't be open (file doesn't exist or is not accesible)");				
				throw new SnakyException("SimpleFile not exists");
			}
			// Log
			log.debug("SimpleFile exist - "+fileName);
		}
		catch(NumberFormatException e){
			log.error("Bad song category, wrong format number for '"+args[1]+"'");
			System.out.println("Error in song category!!");
			System.out.println("Song Category must be an integer number.");
			throw e;
		}
		// Everything's fine, we reach the end of the check function, Wohoo 
	}

	// Main method where we create the rest of the objects.
	// First we transform our file into a memory object to store snakes and relative relations
	// Then we translate the relative relations to real links, having a data structure to run on it
	// Finally, we create the "composer" object and using IoC send it the Snake ensemble previously created
	// Configure and run our composer, the results are available to be read :)
	// 
	// The complete full deepest path from every node is stored in an ArrayList of results
	// The solution is available using a conversion to ArrayList with compareTo override included
	public void run() throws Throwable {
		// The work flow is
		// - Process the file and generate our SnakeEnsemble
		try {
			readSingerSnakes();
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new SnakyException("Problems with the file parametrization, cannot convert to proper Snake Model");
		}
		// - Create our SeneradeComposer and initialize it
		serenade = new SerenadeComposer(ensemble);
		serenade.setCategory(songCategory);
		// - Generate songs
		serenade.run();
	}
	
	// Read the file and populate the Ensemble following the SSS rules :)
	private void readSingerSnakes() throws IOException{
		SimpleFile file = new SimpleFile(fileName);
		String line = "";
		HashMap<String,FileSnake> hashSnakes = new HashMap<String,FileSnake>();

		// Process file
		file.openFile();		
		line = file.readFile();
		while (line!=null){
			log.debug("Readed line - '"+line+"'");
			
			// Store the File representation
			//FileSnake fs = com.aafonso.snakyserenades.utils.Conversion.decodeLine(line);
			FileSnake fs = Conversion.decodeLine(line);
			hashSnakes.put(fs.getName(), fs);
			log.info("FileSnake Decoded - "+fs.toString());
			
			// Create a new Singer (our real final Snake)
			SingerSnake sn = new SingerSnake();
			sn.setName(fs.getName());
			
			// And store into ensemble
			ensemble.addSnake(sn);
			
			// Read line
			line = file.readFile();
		}
		file.closeFile();
		
		// We have a Map with the list of FileSnakes and another with the SingerSnakes registered into
		// our ensemble... lets link 'em 
		for (int i=0; i<ensemble.size();i++){
			SingerSnake ss = (SingerSnake) ensemble.getSnakeAtPos(i);
			FileSnake fs = hashSnakes.get(ss.getName());
			
			if (fs.getHead()!=-1) ss.setHeadEnd(ensemble.getSnakeAtPos(fs.getHead()));
			if (fs.getTail()!=-1) ss.setTailEnd(ensemble.getSnakeAtPos(fs.getTail()));
				
		}
		
		// Only for log purpose... Our relations and conversion from file to Snake Singers linked structure
		for (int i=0; i<ensemble.size();i++){
			log.info("SingerSnake at "+i+" - "+ensemble.getSnakeAtPos(i).toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	public String getResult() {
		List<SongsResults> list = new ArrayList<SongsResults>(serenade.getResultsArray());
		Collections.sort(list,new SortArraySongsResults());
		return list.get(0).getSong();
	}

	@SuppressWarnings("unchecked")
	public int getNumsongs() {
		List<SongsResults> list = new ArrayList<SongsResults>(serenade.getResultsArray());
		Collections.sort(list,new SortArraySongsResults());
		return list.get(0).getNum();
	}	
	
	// Number of elements
	public int getNumSnakes() {
		return serenade.getEnsemble().size();
	}
	
	// Get results
	@SuppressWarnings("rawtypes")
	public List getResultsArray(){
		return serenade.getResultsArray();
	}
	
	/*
	 * Getter & Setter section 
	 */
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getSongCategory() {
		return songCategory;
	}

	public void setSongCategory(int songCategory) {
		this.songCategory = songCategory;
	}

}
