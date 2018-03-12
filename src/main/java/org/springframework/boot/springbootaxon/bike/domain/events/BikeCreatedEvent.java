package org.springframework.boot.springbootaxon.bike.domain.events;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BikeCreatedEvent implements BikeEvent {

	private final String id;
	private final String title;
	private final static Logger logger = LoggerFactory.getLogger(BikeCreatedEvent.class);


	public BikeCreatedEvent(String id, String title) {
		this.id = id;
		this.title = title;
	}

	@Override
	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
}
