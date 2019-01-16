package com.facultyavailability.commons.util;

public enum Protocol {
	
	PROTOCOL_JSON("PROTOCOL_JSON");

	private String protocol;
	
	private Protocol(String protocol) {
		this.protocol = protocol;
	}

	public String getProtocol() {
		return protocol;
	}
	
}
