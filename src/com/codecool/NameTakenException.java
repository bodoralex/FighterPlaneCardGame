package com.codecool;

public class NameTakenException extends Exception {
		
	public String errorMessage(){
		
		return "Name already taken";
	}
	
}
