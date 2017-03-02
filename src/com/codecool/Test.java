package com.codecool;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public static Map<Byte, Integer> sizeNeeded = new HashMap<Byte, Integer>() {
        {
            put(new Byte("1"), 1);
            put(new Byte("2"), 2);
        }
    };

	public static void main(String[] args) {
		System.out.println(null == null);
	}

}
