package com.codecool;

public class NameTakenException extends Exception {

	private static final long serialVersionUID = 1L;

	public String errorMessage() {
		return "Name already taken";
	}

}
