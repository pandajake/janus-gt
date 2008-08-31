package com.janus.models;

public class Model {
	/**
	 * Checks a required <code>String</code> value for general correctness to the data format.
	 * 
	 * @param name the name of the value type being checked.
	 * @param value the value being checked.
	 * @throws IllegalArgumentException if the value being checked is invalid.
	 */
	public static void checkRequired(String name, String value) {
		if(value == null) {
			throw new IllegalArgumentException("Value for \"" + name + "\" must be non-null.");
		}
		if(value.length() == 0) {
			throw new IllegalArgumentException("Value for \"" + name + "\" cannot be empty.");
		}
	}
	
	/**
	 * Checks an optional <code>String</code> value for general correctness to the data format.
	 * 
	 * @param name the name of the value type being checked.
	 * @param value the value being checked.
	 * @throws IllegalArgumentException if the value being checked is invalid.
	 */
	public static void checkOptional(String name, String value) {
		if(value == null) {
			throw new IllegalArgumentException("Value for \"" + name + "\" must be non-null.");
		}
	}
}
