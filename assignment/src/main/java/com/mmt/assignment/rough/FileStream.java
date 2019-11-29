package com.mmt.assignment.rough;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FileStream {
	public static void main(String[] args) {
		String inputFile="/Users/ranjanchoudhury/Downloads/Data_files/Credit_Line.csv";
		try {
			readFile(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readFile(String file) throws IOException {
		Predicate<String> notEmpty = str->str!=null && str.length()>0;
		Stream<String> fileStream = Files.lines(Paths.get(file));
		fileStream.filter(notEmpty).forEach(line->{
			System.out.println(line);
		});
	}
}
