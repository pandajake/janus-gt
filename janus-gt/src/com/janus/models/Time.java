package com.janus.models;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Time {
	public static final DecimalFormat FORMATTER = new DecimalFormat("00");
	
	private int hours, minutes, seconds;
	private boolean guaranteed;
	
	private Time(int hours, int minutes, int seconds, boolean guaranteed) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.guaranteed = guaranteed;
	}
	
	public static Time create(String rawTime) {
		boolean guaranteed = true;
		// preprocess
		if(rawTime.equals("-")) {
			return null;
		} else if (rawTime.startsWith("X")){
			guaranteed = false;
			rawTime = rawTime.substring(1, rawTime.length());
		}
		// get time value.
		int hours = -1;
		int minutes = -1;
		int seconds = -1;
		Scanner lineIn = new Scanner(rawTime);
		lineIn.useDelimiter(":");
		int count = 0;
		for(; count < 3 && lineIn.hasNext(); count++) {
			String current = lineIn.next();
			try {
				switch(count) {
				case 0:
					hours = Integer.parseInt(current);
					break;
				case 1:
					minutes = Integer.parseInt(current);
					break;
				case 2:
					seconds = Integer.parseInt(current);
					break;
				}
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Invalid number value; was \"" +
						rawTime + "\".");
			}
		}
		if(count != 3 || lineIn.hasNext()) {
			throw new IllegalArgumentException("Invalid number value; was \"" +
					rawTime + "\".");
		}
		return new Time(hours, minutes, seconds, guaranteed);
	}
	
	public String toTime(int minuteAdjustment) {
		int localHours = hours;
		int localMinutes = minutes + minuteAdjustment;
		while(localMinutes >= 60) {
			localHours++;
			localMinutes -= 60;
		}
		while(localMinutes < 0) {
			localHours--;
			localMinutes += 60;
		}
		return FORMATTER.format(localHours) + ":" + FORMATTER.format(localMinutes) + 
			":" + FORMATTER.format(seconds);
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * @return the seconds
	 */
	public int getSeconds() {
		return seconds;
	}

	/**
	 * @return the guaranteed
	 */
	public boolean isGuaranteed() {
		return guaranteed;
	}
}
