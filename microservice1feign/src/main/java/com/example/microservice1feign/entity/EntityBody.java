package com.example.microservice1feign.entity;

public class EntityBody {

	private String properties;

	public EntityBody() {
	}

	public EntityBody(String properties) {
		this.properties = properties;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

}
