package com.janus.models;

/**
 * Model representing a line in <code>stops.txt</code>.
 * 
 * @see {@link http://code.google.com/transit/spec/transit_feed_specification.html#stops_txt___Field_Definitions}
 * 
 * @author phoebus12@gmail.com
 */
public class Stop {
	private String id;
	private String name;
	private String hiddenName;
	private String description;
	private double lat;
	private double lon;
	private String zoneId;
	private String url;
	
	public Stop(String id,
			String name,
			String hiddenName,
			String description,
			double lat,
			double lon,
			String zoneId,
			String url) {
		setId(id);
		setName(name);
		setHiddenName(hiddenName);
		setDescription(description);
		setLat(lat);
		setLon(lon);
		setZoneId(zoneId);
		setUrl(url);
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the hiddenName
	 */
	public String getHiddenName() {
		return hiddenName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @return the lon
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the zone_id
	 */
	public String getZoneId() {
		return zoneId;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Model.checkOptional("Description", description);
		this.description = description;
	}

	/**
	 * @param hiddenName the hiddenName to set
	 */
	public void setHiddenName(String hiddenName) {
		Model.checkOptional("Hidden Name", hiddenName);
		this.hiddenName = hiddenName;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		Model.checkRequired("Code", id);
		this.id = id;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		Model.checkRequired("Latitude", "" + lat);
		this.lat = lat;
	}

	/**
	 * @param lon the lon to set
	 */
	public void setLon(double lon) {
		Model.checkRequired("Longitude", "" + lon);
		this.lon = lon;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Model.checkRequired("Name", name);
		this.name = name;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		Model.checkOptional("URL", url);
		this.url = url;
	}

	/**
	 * @param zone_id the zone_id to set
	 */
	public void setZoneId(String zoneId) {
		Model.checkOptional("Zone ID", zoneId);
		this.zoneId = zoneId;
	}
}
