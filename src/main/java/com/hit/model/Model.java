package com.hit.model;

public interface Model {
	public void buildSearchName(String bookNameString, String authorNameString);

	public void connectedToServer();

	public void sendRequestToServer();

	public void getResponseFromServer();

	public String getResponse();
}
