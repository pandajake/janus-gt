package com.janus.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.janus.models.Pattern;
import com.janus.models.Time;
import com.janus.models.Trip;

public class TripController {
	public static final int MINIMUM_KEYS = 4;
	
	public static Trip create(HashMap<String, Pattern> patternMap, String inputLine) {
		Scanner lineIn = new Scanner(inputLine);
		lineIn.useDelimiter(",");
		List<String> keys = new ArrayList<String>();
		while(lineIn.hasNext()) {
			keys.add(ControllerUtilities.parseToken(lineIn.next()));
		}
		if(keys.size() < MINIMUM_KEYS) {
			System.out.println("FAILURE: Invalid number of keys, was \"" + 
					inputLine + "\".");
			return null;
		}
		// got the minimum number of tokens.  Process the remainder into time values
		ArrayList<String> rawTimes = new ArrayList<String>();
		for(int i = MINIMUM_KEYS; i < keys.size(); i++) {
			rawTimes.add(keys.get(i));
		}
		// see if we have a matching pattern for this time.
		Pattern match = patternMap.get(keys.get(0));
		if(match == null) {
			System.out.println("FAILURE: Could not find a pattern match for a trip; was trip \"" + 
					keys.get(1) + "\".");
			return null;
		}
		// create times list
		ArrayList<Time> times = new ArrayList<Time>();
		times.ensureCapacity(rawTimes.size());
		for(String rawTime : rawTimes) {
			if(rawTime.length() > 0) {
				try {
					times.add(Time.create(rawTime));
				} catch (Exception e) {
					System.out.println("FAILURE: " + e.getMessage());
					return null;
				}
			}
		}
		// create trip
		return new Trip(match,
				keys.get(1),
				keys.get(2),
				keys.get(3),
				times);
	}
}
