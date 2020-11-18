package com.project.MatchingPro.config.exception;

public class NotDataToDB extends Exception{

private String message;
	
	public NotDataToDB(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
