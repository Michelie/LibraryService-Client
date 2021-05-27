package com.hit.model;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import com.google.gson.Gson;
import com.hit.controller.StringMatchingController;

public class StringMatchingModel implements Model {
	private Socket myServer;
	private RequestClient reqClient;
	private Gson gson;
	private DataInputStream input = null;
	private DataOutputStream output = null;
	private String action;
	private String bookNameString;
	private String authorNameString;
	private int algoSelection;
	private String messageIn;
	private ResponseClient resClient;
	private String jsonString = null;
	private String searchName;

	public StringMatchingModel(String action, int algoSelection, String bookNameString, String authorNameString) {
		connectedToServer();
		this.action = action;
		this.algoSelection = algoSelection;
		this.bookNameString = bookNameString;
		this.authorNameString = authorNameString;
		this.gson = new Gson();

		if (action.equals("SEARCH")) {
			buildSearchName(bookNameString, authorNameString);
			reqClient = new RequestClient(action, searchName, algoSelection);
		}
		// if the action is ADD, there's no need to update searchName
		else {
			reqClient = new RequestClient(action, bookNameString, authorNameString, algoSelection);

		}
		sendRequestToServer();
		getResponseFromServer();

	}

	public StringMatchingModel(String action, int algoSelection, String bookNameString) {
		// C-tor for DELETE/REMOVE
		connectedToServer();
		this.action = action;
		this.algoSelection = algoSelection;
		this.bookNameString = bookNameString;
		this.authorNameString = null;
		this.gson = new Gson();

		buildSearchName(bookNameString, authorNameString);
		reqClient = new RequestClient(action, searchName, algoSelection);
		sendRequestToServer();
		getResponseFromServer();

	}

	@Override
	public void buildSearchName(String bookNameString, String authorNameString) {
		if (bookNameString == null) {
			searchName = ("author='").concat(authorNameString);
			//System.out.println(searchName + " from buildSearchName");
		} else {
			if (authorNameString == null) {
				searchName = ("name='").concat(bookNameString);
				//System.out.println(searchName + " from buildSearchName");

			} else {
				searchName = ((("Book{name='").concat(bookNameString)).concat("', author='")).concat(authorNameString);
				//System.out.println(searchName + " from buildSearchName");
			} 
			// If both are not null
		}
	}

	@Override
	public void connectedToServer() {
		try {
			myServer = new Socket("localhost", 34567);
			System.out.println("Connected to server");
			output = new DataOutputStream(myServer.getOutputStream());
			input = new DataInputStream(new BufferedInputStream(myServer.getInputStream()));
		} catch (IOException e) {
			System.out.println("Can't connect to server");
			e.printStackTrace();
		}

	}

	@Override
	public void sendRequestToServer() {
		try {
			System.out.println(reqClient.body.getName());
			jsonString = (gson.toJson(reqClient));
			output.writeUTF(jsonString);
		} catch (IOException e) {
			System.out.println("Can't send request to server");
			e.printStackTrace();
		}
	}

	@Override
	public void getResponseFromServer() {
		try {
			messageIn = input.readUTF();
			resClient = gson.fromJson(messageIn, ResponseClient.class); // the response from server

		} catch (IOException e) {
			System.out.println("Can't get response from server");
			e.printStackTrace();
		}
	}

	@Override
	public String getResponse() {
		return resClient.getBody();
	}
}
