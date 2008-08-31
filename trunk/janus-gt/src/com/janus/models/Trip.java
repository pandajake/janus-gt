package com.janus.models;

/**
 * Model representing an entry in the <code>trips.txt</code> file.
 * 
 * This model deviates from the standard GTFS paradigm by introducing the concept of 
 * service &quot;patterns&quot;.  See {@link Pattern} for more information.
 * 
 * @see {@link http://code.google.com/transit/spec/transit_feed_specification.html#trips_txt___Field_Definitions}
 * 
 * @author William Adama
 */
public class Trip {
	public enum Direction {
		INBOUND(0),
		OUTBOUND(1);
		
		private int value;
		
		private Direction(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	
	private String id;
	private String calendar;
	private Pattern pattern;
	private String block;
	private Direction direction;
	
	public Trip(String id,
			String calendar,
			Pattern pattern,
			String block,
			Direction direction) {
		setId(id);
		setCalendar(calendar);
		setPattern(pattern);
		setBlock(block);
		setDirection(direction);
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
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
