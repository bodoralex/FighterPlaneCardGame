package com.codecool;

import java.util.ArrayList;
import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(0);
		a.add(1);
		a.add(2);
		a.add(3);
		Iterator<Integer> b = a.iterator();
		b.forEachRemaining(System.out::println);
		b.forEachRemaining(System.out::println);
		
	}

}
