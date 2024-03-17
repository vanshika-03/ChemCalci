package com.chem.calci.model;

import java.util.List;

public class Response {
	private int statusCode;
	private String error;
	private List<Float> response;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public List<Float> getResponse() {
		return response;
	}
	public void setResponse(List<Float> response) {
		this.response = response;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
