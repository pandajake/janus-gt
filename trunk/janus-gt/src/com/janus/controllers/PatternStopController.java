package com.janus.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.janus.models.PatternStop;

public class PatternStopController {
public static final int REQUIRED_KEYS = 8;
	
	public static PatternStop create(String inputLine) {
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
			return new PatternStop(keys.get(0),
					keys.get(1),
					Integer.parseInt(keys.get(2)),
					keys.get(3).equals("1"),
					Integer.parseInt(keys.get(4)),
					keys.get(5),
					Integer.parseInt(keys.get(6)),
					Integer.parseInt(keys.get(7)));
		} catch (NumberFormatException e) {
			System.out.println("FAILURE: " + e.getMessage());
			return null;
		}
	}
}
