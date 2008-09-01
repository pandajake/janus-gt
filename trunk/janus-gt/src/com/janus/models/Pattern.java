package com.janus.models;

import java.util.ArrayList;
import java.util.List;

public class Pattern extends Model {
	private String name;
	private String headsign;
	private String shape;
	private String route;
	private List<PatternStop> stops;
	private List<String> times;
	
	public Pattern(String name,
			String headsign,
			String shape,
			String route,
			List<PatternStop> stops) {
		setName(name);
		setHeadsign(headsign);
		setShape(shape);
		setRoute(route);
		setStops(stops);
		setTimes(new ArrayList<String>());
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the headsign
	 */
	public String getHeadsign() {
		return headsign;
	}

	/**
	 * @param headsign the headsign to set
	 */
	public void setHeadsign(String headsign) {
		this.headsign = headsign;
	}

	/**
	 * @return the shape
	 */
	public String getShape() {
		return shape;
	}

	/**
	 * @param shape the shape to set
	 */
	public void setShape(String shape) {
		this.shape = shape;
	}

	/**
	 * @return the route
	 */
	public String getRoute() {
		return route;
	}

	/**
	 * @param route the route to set
	 */
	public void setRoute(String route) {
		this.route = route;
	}

	/**
	 * @return the stops
	 */
	public List<PatternStop> getStops() {
		return stops;
	}

	/**
	 * @param stops the stops to set
	 */
	public void setStops(List<PatternStop> stops) {
		this.stops = stops;
	}

	/**
	 * @return the times
	 */
	public List<String> getTimes() {
		return times;
	}

	/**
	 * @param times the times to set
	 */
	public void setTimes(List<String> times) {
		this.times = times;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
