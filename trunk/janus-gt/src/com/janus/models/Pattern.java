package com.janus.models;

import java.util.ArrayList;
import java.util.List;

public class Pattern {
	private String id;
	private String name;
	private String route;
	private String headsign;
	private int direction;
	private String shape;
	
	private List<PatternStop> stops;
	
	public Pattern(String id,
			String name,
			String route,
			String headsign,
			int direction,
			String shape) {
		setId(id);
		setName(name);
		setRoute(route);
		setHeadsign(headsign);
		setDirection(direction);
		setShape(shape);
		setStops(new ArrayList<PatternStop>());
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(int direction) {
		this.direction = direction;
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
	 * @return the number of timepoints specified for this pattern.
	 */
	public int getNumberOfTimepoints() {
		int count = 0;
		for(PatternStop stop : getStops()) {
			if(stop.isTimepoint()) {
				count++;
			}
		}
		return count;
	}
}
