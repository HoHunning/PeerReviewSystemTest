package com.example.demo.PeerReviewSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OutputParser {
	
	public static String parse(String str) {
		String line = str.replaceAll("\\s+", "");
		line = line.replaceAll("\\’", "");
		line = line.replaceAll("\\'", "");
		return line;
	}

	public static String fileToString(String fileName) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");
		try {
			while((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
	
			return stringBuilder.toString();
		} finally {
			reader.close();
		}
	}
}
