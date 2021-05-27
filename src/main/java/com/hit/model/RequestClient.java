package com.hit.model;

public class RequestClient {
	public Headers headers;
	public Body body;

	public RequestClient(String action, String bookNameString, String authorNameString, int algoSelection) {
		headers = new Headers(action);
		body = new Body(bookNameString, authorNameString, algoSelection);
	}

	public RequestClient(String action, String searchName, int algoSelection) {
		headers = new Headers(action);
		body = new Body(searchName, algoSelection);
	}
}

class Headers {
	public String action;

	public Headers(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}
}

class Body {
	public String name;
	public String author;
	int selectedAlgo;

	public Body(String name, String author, int selectedAlgo) {
		this.name = name;
		this.author = author;
		this.selectedAlgo = selectedAlgo;
	}

	public Body(String name, int selectedAlgo) {
		this.name = name;
		this.selectedAlgo = selectedAlgo;
		this.author = null;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public int getSelectedAlgo() {
		return selectedAlgo;
	}
}