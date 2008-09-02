package com.janus.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.janus.Janus;
import com.janus.controllers.PatternController;
import com.janus.controllers.PatternStopController;
import com.janus.models.Pattern;
import com.janus.models.PatternStop;
import com.janus.models.Trip;

public class DataStore {
	private boolean valid;
	
	private List<Pattern> patterns;
	private List<Trip> trips;
	
	public DataStore(File patternsFile, 
			File patternStopsFile,
			File tripsFile) {
		// set to invalid unless we become done at some later point.
		valid = false;
		Scanner patternsIn, patternStopsIn, tripsIn;
		try {
			patternsIn = new Scanner(patternsFile);
			patternStopsIn = new Scanner(patternStopsFile);
			tripsIn = new Scanner(tripsFile);
		} catch (FileNotFoundException e) {
			System.out.println("FAILURE: Cannot read: \"" + e.getMessage() + "\"");
			System.out.println("    Make sure that all of the required files are present in the input directory.");
			return;
		}
		// scanners ready, begin reading in.
		// start with patterns, one for each line.
		// use a hash map, since later we'll need to use the names of the 
		// patterns to input the pattern stops from the file.
		HashMap<String, Pattern> patternMap = new HashMap<String, Pattern>();
		// start reading.
		while(patternsIn.hasNextLine()) {
			Pattern read = PatternController.create(patternsIn.nextLine());
			if(read == null) {
				System.out.println("FAILURE: Unrecoverable failure, stopping further processing.");
				return;
			} else if(patternMap.containsKey(read.getId())) {
				// key already exists; error
				System.out.println("FAILURE: Found duplicate pattern IDs in \"" + 
						Janus.PATTERNS_INPUT + "\", stopping further processing.");
				return;
			} else {
				// looks OK from a duplication standpoint, add
				patternMap.put(read.getId(), read);
			}
		}
		// have all patterns.  get pattern stops.
		while(patternStopsIn.hasNextLine()) {
			PatternStop read = PatternStopController.create(patternStopsIn.nextLine());
			if(read == null) {
				System.out.println("FAILURE: Unrecoverable failure, stopping further processing.");
				return;
			}
			Pattern match = patternMap.get(read.getPatternId());
			if(match == null) {
				System.out.println("FAILURE: Unable to match pattern stop to a pattern, was \"" + 
						read.getPatternId() + "\".");
				return;
			}
			match.getStops().add(read);
		}
		// copy patterns from map to normal list
		ArrayList<Pattern> patternsTemp = new ArrayList<Pattern>();
		patternsTemp.ensureCapacity(patternMap.keySet().size());
		for(String key : patternMap.keySet()) {
			patternsTemp.add(patternMap.get(key));
		}
		patterns = patternsTemp;
		//
		System.out.println();
	}
}
