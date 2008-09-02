package com.janus.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.janus.models.Pattern;

public class PatternController {
	public static final int REQUIRED_KEYS = 6;
	
	public static Pattern create(String inputLine) {
		Scanner lineIn = new Scanner(inputLine);
		lineIn.useDelimiter(",");
		List<String> keys = new ArrayList<String>();
		while(lineIn.hasNext()) {
			keys.add(ControllerUtilities.parseToken(lineIn.next()));
		}
		if(keys.size() != REQUIRED_KEYS) {
			System.out.println("FAILURE: Invalid number of keys, was \"" + 
					inputLine + "\".");
			return null;
		}
		// got the right number of tokens, continue.
		try {
			return new Pattern(keys.get(0),
					keys.get(1),
					keys.get(2),
					keys.get(3),
					Integer.parseInt(keys.get(4)),
					keys.get(5));
		} catch (NumberFormatException e) {
			System.out.println("FAILURE: Illegal value for \"direction_id\", was \"" + 
					keys.get(4) + "\".");
			return null;
		}
	}
}
