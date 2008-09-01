package com.janus.data;

import java.util.List;

import com.janus.models.Pattern;
import com.janus.models.Stop;
import com.janus.models.Trip;

public class DataStore {
	private List<Stop> stops;
	private List<Pattern> patterns;
	private List<Trip> trips;
	
	public DataStore(List<Stop> stops,
			List<Pattern> patterns,
			List<Trip> trips) {
		this.stops = stops;
		this.patterns = patterns;
		this.trips = trips;
	}

	/**
	 * @return the stops
	 */
	public List<Stop> getStops() {
		return stops;
	}

	/**
	 * @return the patterns
	 */
	public List<Pattern> getPatterns() {
		return patterns;
	}

	/**
	 * @return the trips
	 */
	public List<Trip> getTrips() {
		return trips;
	}
}
