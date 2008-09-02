package com.janus;

import java.io.File;

import com.janus.data.DataStore;
import com.janus.data.DataWriter;

public class Janus {
	public static final File INPUT_DIRECTORY = new File("input");
	public static final File PATTERNS_INPUT = new File("input/patterns.csv");
	public static final File PATTERN_STOPS_INPUT = new File("input/pattern_stops.csv");
	public static final File TRIPS_INPUT = new File("input/trips.csv");
	public static final File OUTPUT_DIRECTORY = new File("output");
	public static final File STOP_TIMES_OUTPUT = new File("output/stop_times.txt");
	public static final File TRIPS_OUTPUT = new File("output/trips.txt");
	
	private static final String HEADER = "Janus GT 0.1b";
	
	public static void main(String[] args) {
		new Janus();
	}
	
	public Janus() {
		// print out nice pretty header for system invokation.
		System.out.println();
		System.out.println(HEADER);
		System.out.println();
		// make sure directories exist and are writable.
		if(INPUT_DIRECTORY.exists() && OUTPUT_DIRECTORY.isDirectory() && 
				INPUT_DIRECTORY.canRead()) {
			// ok
		} else {
			System.out.println("FAILURE: Cannot read input directory!  Make sure the input " +
				"directory exists, and is readable.");
			return;
		}
		if(OUTPUT_DIRECTORY.exists() && OUTPUT_DIRECTORY.isDirectory() && 
				OUTPUT_DIRECTORY.canWrite()) {
			// ok
		} else {
			System.out.println("FAILURE: Cannot write to output directory!  Make sure the output " +
				"directory exists, and is writable.");
			return;
		}
		// try reading input files
		DataStore data = new DataStore(PATTERNS_INPUT,
				PATTERN_STOPS_INPUT,
				TRIPS_INPUT);
		if(data.isValid()) {
			System.out.println("Data appears to be valid, writing...");
			boolean status = DataWriter.write(data, STOP_TIMES_OUTPUT, TRIPS_OUTPUT);
			if(status) {
				System.out.println("DONE!!!");
			} else {
				System.out.println("WARNING: Errors were generated, please correct and rerun.");
			}
		} else {
			System.out.println("FAILURE: Errors occured, please correct and retry.");
		}
	}
}
