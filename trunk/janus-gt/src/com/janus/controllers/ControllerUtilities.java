package com.janus.controllers;

public class ControllerUtilities {
	/**
	 * Does very basic token parsing from a CSV file token.
	 * 
	 * @param token the token to parse.
	 * @return the raw value.
	 */
	public static String parseToken(String token) {
		if(token.startsWith("\"")) {
			return token.trim().substring(1, token.length() - 1);
		} else {
			return token.trim();
		}
	}
}
