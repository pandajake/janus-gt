package com.janus.models;

import java.util.List;

public class Trip {
	private String id;
	private String calendar;
	private Pattern pattern;
	private String block;
	private List<Time> times;
	
	public Trip(Pattern pattern,
			String id,
			String calendar,
			String block,
			List<Time> times) {
		setId(id);
		setCalendar(calendar);
		setPattern(pattern);
		setBlock(block);
		setTimes(times);
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
	 * @return the calendar
	 */
	public String getCalendar() {
		return calendar;
	}

	/**
	 * @param calendar the calendar to set
	 */
	public void setCalendar(String calendar) {
		this.calendar = calendar;
	}

	/**
	 * @return the pattern
	 */
	public Pattern getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	/**
	 * @return the block
	 */
	public String getBlock() {
		return block;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(String block) {
		this.block = block;
	}

	/**
	 * @return the times
	 */
	public List<Time> getTimes() {
		return times;
	}

	/**
	 * @param times the times to set
	 */
	public void setTimes(List<Time> times) {
		this.times = times;
	}
}
