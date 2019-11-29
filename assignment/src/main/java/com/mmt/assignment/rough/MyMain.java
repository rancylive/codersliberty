package com.mmt.assignment.rough;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MyMain {

	public static void main(String[] args) {
		System.out.println("Good mrng");
		List<List<Integer>> matric = new ArrayList<List<Integer>>();
		matric.add(Arrays.asList(1,3,1));
		matric.add(Arrays.asList(5,2,9));
		matric.add(Arrays.asList(8,7,6));
		System.out.println(count(matric));
		//System.out.println(getShrunkArray(Arrays.asList("a","b","c","c","c","d"), 3));
		//System.out.println(getShrunkArray(Arrays.asList("a","b","d","e","e"), 3));
		System.out.println(getShrunkArray(Arrays.asList("a","b","c","d","e","e","e","e","d","d","c","b","f","g","f"), 4));
	}
	public static List<String> getMinimizedArray(List<String> inputArray, int burstLength) {
		List<String> list = new LinkedList<String>();
		String lastChar="";
		int count=1;
		for(int i=0;i<inputArray.size();i++) {
			String currChar = inputArray.get(i);
			list.add(currChar);
			if(lastChar == currChar) {
				count++;
			} else {
				if(count>=burstLength) {
					int size=list.size();
					for(int j=1;j<=count;j++) {
						list.remove(size-1-j);
					}
				}
				count=1;
			}
			if(i>=inputArray.size()-1) {
				int size=list.size();
				if(count>=burstLength) {
					for(int j=0;j<burstLength;j++) {
						list.remove(size-j);
					}
				}
			}
			lastChar=currChar;
			
		}
		return list;
    }
	
	public static List<String> getShrunkArray(List<String> inputArray, int burstLength) {
		List<String> result = getMinimizedArray(inputArray, burstLength);
		int inputSize = inputArray.size();
		while(result.size()!=inputSize) {
			inputSize = result.size();
			result = getMinimizedArray(result, burstLength);
		}
		return result;
	}
	
	public static int isMaxRow(List<Integer> list, Integer num, int colMax, int colMin) {
		for(int i=0;i<list.size();i++) {
			int n=list.get(i);
			if(n==colMax || n==colMin) {
            	return -1;
            }
            if(n>colMax) {
            	colMax = n;
            }
            if(n<colMin) {
            	colMin=n;
            }
		}
		if(num==colMax || num==colMin) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public static int isMaxCol(List<List<Integer>> matrix, Integer num, int col, int colMax, int colMin) {
		for(int j=0;j<matrix.size();j++) {
			List<Integer> list=matrix.get(j);
				int n=list.get(col);
				if(n==colMax || n==colMin) {
	            	return -1;
	            }
	            if(n>colMax) {
	            	colMax = n;
	            }
	            if(n<colMin) {
	            	colMin=n;
	            }
		}
		if(num==colMax || num==colMin) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public static int count(List<List<Integer>> matrix) {
		int distinctElements = 0;
		int colMax=Integer.MIN_VALUE;
        int colMin = Integer.MAX_VALUE;
       
        for(int i=0;i<matrix.size();i++) {
        	List<Integer> subList = matrix.get(i);
            int rowMax=Integer.MIN_VALUE;
            int rowMin = Integer.MAX_VALUE;
            for(int j=0;j<subList.size();j++) {
            	int num = subList.get(j);
            	if(isMaxRow(subList, num, rowMax, rowMin)==1 || isMaxCol(matrix, num, j, colMax, colMin)==1) {
            		distinctElements++;
            	} else if(isMaxRow(subList, num, rowMax, rowMin)==-1 || isMaxCol(matrix, num, j, colMax, colMin)==-1) {
            		return -1;
            	}
            }
        }
        return distinctElements;
	}
}
