package com.techelevator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;



public class Writer {
	
	//PrintWriter fileWriter = new BufferedWriter(new FileWriter("Log.txt", true));
	
	private static final String FILE_PATH = "Log.txt";
	
	File file = new File(FILE_PATH);
	
	public void writeFile(String printToFile) {
		try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("Log.txt", true));){ 
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			formatter.format(date);
			
			fileWriter.append(date + " " + printToFile);
			fileWriter.newLine();
			
		} catch (FileNotFoundException e){
			System.out.println("This should NEVER happen!");
		} catch (IOException e1) {
			System.out.println("This should NEVER happen!");
		}
	}

}
