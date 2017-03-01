package com.codecool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test {
	
	public static Map<Byte, Integer> sizeNeeded = new HashMap<Byte, Integer>() {

        {
            put(new Byte("1"), 1);
            put(new Byte("2"), 2);
        }

        ;
    };

	public static void main(String[] args) {
		Scanner scannr = new Scanner(System.in);
		String input = scannr.next();
        Integer playerChoice = Integer.parseInt(input);
		System.out.println(playerChoice);
		scannr.close();
	}

}
