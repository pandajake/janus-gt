package com.janus.models;

public class PatternStop extends Model {
	public enum ServiceType {
		REGULAR(0),
		NO_SERVICE(1),
		PHONE_AGENCY(2),
		TALK_TO_DRIVER(3);
		
		private int value;
		
		private ServiceType(int value) {
			this.value = value;
		}

		/**
		 * @return the value
		 */
		public int getValue() {
			return value;
		}
	}
	
	private Stop stop;
	private String headsign;
	private int sequence;
	private boolean timepoint;
	private ServiceType pickup;
	private ServiceType dropoff;
	
	private PatternStop(Stop stop,
			String headsign,
			int sequence,
			boolean timepoint,
			ServiceType pickup,
			ServiceType dropoff) {
		setStop(stop);
		setHeadsign(headsign);
		setSequence(sequence);
		setTimepoint(timepoint);
		setPickup(pickup);
		setDropoff(dropoff);
	}
	
	/**
	 * @return the stop
	 */
	public Stop getStop() {
		return stop;
	}
	/**
	 * @param stop the stop to set
	 */
	public void setStop(Stop stop) {
		this.stop = stop;
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
	 * @return the sequence
	 */
	public int getSequence() {
		return sequence;
	}
	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	/**
	 * @return the timepoint
	 */
	public boolean isTimepoint() {
		return timepoint;
	}
	/**
	 * @param timepoint the timepoint to set
	 */
	public void setTimepoint(boolean timepoint) {
		this.timepoint = timepoint;
	}
	/**
	 * @return the pickup
	 */
	public ServiceType getPickup() {
		return pickup;
	}
	/**
	 * @param pickup the pickup to set
	 */
	public void setPickup(ServiceType pickup) {
		this.pickup = pickup;
	}
	/**
	 * @return the dropoff
	 */
	public ServiceType getDropoff() {
		return dropoff;
	}
	/**
	 * @param dropoff the dropoff to set
	 */
	public void setDropoff(ServiceType dropoff) {
		this.dropoff = dropoff;
	}
}
