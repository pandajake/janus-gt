package com.janus.models;

public class PatternStop {
	private String patternId;
	private String stop;
	private int sequence;
	private boolean timepoint;
	private int arrivalOffset;
	private String headsign;
	private int pickup;
	private int dropoff;
	
	public PatternStop(String patternId,
			String stop,
			int sequence,
			boolean timepoint,
			int arrivalOffset,
			String headsign,
			int pickup,
			int dropoff) {
		setPatternId(patternId);
		setStop(stop);
		setSequence(sequence);
		setTimepoint(timepoint);
		setArrivalOffset(arrivalOffset);
		setHeadsign(headsign);
		setPickup(pickup);
		setDropoff(dropoff);
	}

	/**
	 * @return the stop
	 */
	public String getStop() {
		return stop;
	}

	/**
	 * @param stop the stop to set
	 */
	public void setStop(String stop) {
		this.stop = stop;
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
	 * @return the arrivalOffset
	 */
	public int getArrivalOffset() {
		return arrivalOffset;
	}

	/**
	 * @param arrivalOffset the arrivalOffset to set
	 */
	public void setArrivalOffset(int arrivalOffset) {
		this.arrivalOffset = arrivalOffset;
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
	 * @return the pickup
	 */
	public int getPickup() {
		return pickup;
	}

	/**
	 * @param pickup the pickup to set
	 */
	public void setPickup(int pickup) {
		this.pickup = pickup;
	}

	/**
	 * @return the dropoff
	 */
	public int getDropoff() {
		return dropoff;
	}

	/**
	 * @param dropoff the dropoff to set
	 */
	public void setDropoff(int dropoff) {
		this.dropoff = dropoff;
	}

	/**
	 * @return the patternId
	 */
	public String getPatternId() {
		return patternId;
	}

	/**
	 * @param patternId the patternId to set
	 */
	public void setPatternId(String patternId) {
		this.patternId = patternId;
	}
}
