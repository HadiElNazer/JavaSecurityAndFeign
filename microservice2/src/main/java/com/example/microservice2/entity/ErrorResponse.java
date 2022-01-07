package com.example.microservice2.entity;

public class ErrorResponse {
	private int status;
	private long timeStamp;
	private String message;

	public ErrorResponse() {

	}

	public ErrorResponse(int status, long timeStamp, String message) {
		this.status = status;
		this.timeStamp = timeStamp;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
