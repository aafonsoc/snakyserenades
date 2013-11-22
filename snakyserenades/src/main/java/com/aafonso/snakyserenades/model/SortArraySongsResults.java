/*
 * SortArraySongsResults
 * 
 * 20131121 Alfonso Afonso
 * 
 * Advance Sorted Class to get a sorted List with the results
 * 
 */
package com.aafonso.snakyserenades.model;

import java.util.Comparator;

public class SortArraySongsResults implements Comparator<SongsResults>
{
	public int compare(SongsResults p1, SongsResults p2) 
	{
		return (p2.getNum() - p1.getNum());
	}
}


