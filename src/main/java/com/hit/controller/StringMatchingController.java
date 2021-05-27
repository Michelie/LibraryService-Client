package com.hit.controller;

import com.hit.model.StringMatchingModel;

public class StringMatchingController implements Controller {
	StringMatchingModel stringMatchingModel;
	String responseString = null;

	public StringMatchingController(String action, int algoSelection, String bookNameString, String authorNameString) {

		stringMatchingModel = new StringMatchingModel(action, algoSelection, bookNameString, authorNameString);
	}

	public StringMatchingController(String action, int algoSelection, String bookNameString) {

		stringMatchingModel = new StringMatchingModel(action, algoSelection, bookNameString);
	}

	public String getResponse() {
		responseString = stringMatchingModel.getResponse();
		if(responseString.equals("")) {
			return "Sorry, book not found!";
		}
		return responseString;
	}

}
