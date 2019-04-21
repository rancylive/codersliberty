package com.mmt.assignment.rough;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadmeGenerator {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("/Users/rchoudhury/part.tsv"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/rchoudhury/part_out.tsv"));
		String line;
		writer.write("### Source-Target Mapping:\n\n");
		writer.write("| SOURCE_COLUMN	| TARGET_COLUMN|\n");
		writer.write("| ------ | ----- |\n");
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split("	");
			String outStr = "| " +parts[0] + "." + parts[1] + " | " + parts[2] + ":" + parts[3]+"\n";
			writer.write(outStr);
		}
		reader.close();
		writer.close();
	}
}
