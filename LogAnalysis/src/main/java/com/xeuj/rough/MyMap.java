package com.xeuj.rough;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MyMap {
    public static void main(String[] args) {
        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("Hi", "1");
        String value = stringStringMap.get("Hi");
        String value1 = stringStringMap.get("Hellossss");
        System.out.println(value1);
    }
}
