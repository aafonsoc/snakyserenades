/*
 * SerenadeComposer
 * 
 * 20131121 Alfonso Afonso
 * 
 * Working class to solve the problem.
 * 
 * Using IoC by constructor (also we allow change it by parameter) 
 * we have to do a deep search into our graph data controlling:
 * 
 * - number of times that a snake can sing
 * - availability to sing (not always the snake would like to)
 * - manage to store all the different path to return the proper result
 * 
 * BUT as we are looking for the maximum song possible for the given ensemble
 * and song category request, we assume always that all the snakes would like to
 * sing for us while we are exploring the graph, controlling only the number of 
 * times that a snake can sing.
 * 
 */
package com.aafonso.snakyserenades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aafonso.snakyserenades.model.SingerSnake;
import com.aafonso.snakyserenades.model.SingerSnakeEnsemble;
import com.aafonso.snakyserenades.model.SongsResults;

public class SerenadeComposer {
	
	// Log 
	protected Logger log = LoggerFactory.getLogger(SerenadeComposer.class);
	// The Snakes :)
	private SingerSnakeEnsemble ensemble;	
	// Song category
	private int category = 0;
	// Map structure to control visits
	HashMap<String,Integer> visits = new HashMap<String,Integer>();
	// Results
	private ArrayList<SongsResults> resultsArray = new ArrayList<SongsResults>();
	int numsongs;

	// Constructor
	public SerenadeComposer(SingerSnakeEnsemble ensemble){
		this.ensemble=ensemble;
	}
	
	// Main method... deep search :)
	public void run(){
		// The process is
		// - Pick up every Snake as root
		// - Update the visits number
		// - Search for the head and tail links, if they are available, then search again using them as root
		// - Update the new root as visits and continue like the previous step
		// - We can't go forward for one of these reasons:
		// 		- Because this snakes has no links
		//		- We have reached the max number of times a snake can sing
		// - When there is no chance to follow from the actual node to another new, then return the song
		// - In return, we have got the path we follow
		
		// Loop from every node
		for (int i=0; i<ensemble.size();i++){
			// Info
			log.info("Starting deep search with SingerSnake at "+i+" - "+ensemble.getSnakeAtPos(i).toString());
			// Snake
			SingerSnake sn = (SingerSnake) ensemble.getSnakeAtPos(i);
			// Initialize visits from this node
			for (int j=0; j<ensemble.size();j++) 
				visits.put(ensemble.getSnakeAtPos(j).getName(), 0);
			// Initialize numsongs 
			numsongs=0;
			// Deep search
			visits.put(sn.getName(), 1);
			numsongs++;
			String res = deepSearch(sn);
			// We store also all the results... for testing purpose 
			SongsResults sres = new SongsResults();
			sres.setNum(numsongs);
			sres.setSong(res);
			resultsArray.add(sres);
			// Log
			log.info("Path found with "+numsongs+" songs and formed by '"+res+"' snakes");
		}
	}
	
	public String deepSearch(SingerSnake sn){
		String heads = "", tails = "";
		// Head Side
		if (sn.getHeadEnd()!=null) 
		{
			// Update visits
			int i = visits.get(sn.getHeadEnd().getName());
			if (i<category+1) {
				visits.put(sn.getHeadEnd().getName(), i+1);
				// Returns actual node plus the following path
				numsongs++;
				heads = "," + (String) deepSearch((SingerSnake) sn.getHeadEnd());
			}
		}		
		// Tail Side
		if (sn.getTailEnd()!=null) 
		{
			// Update visits
			int i = visits.get(sn.getTailEnd().getName());
			if (i<category+1) {
				visits.put(sn.getTailEnd().getName(), i+1);
				// Returns actual node plus the following path
				numsongs++;
				tails = "," +  (String) deepSearch((SingerSnake) sn.getTailEnd());
			}
		}
		// Return value
		return sn.getName() + heads + tails;
	}

	// Get resultsArray
	@SuppressWarnings("rawtypes")
	public List getResultsArray(){
		return resultsArray;
	}
	
	/*
	 * Getter & Setter section 
	 */
	
	public SingerSnakeEnsemble getEnsemble() {
		return ensemble;
	}

	public void setEnsemble(SingerSnakeEnsemble ensemble) {
		this.ensemble = ensemble;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}


}
