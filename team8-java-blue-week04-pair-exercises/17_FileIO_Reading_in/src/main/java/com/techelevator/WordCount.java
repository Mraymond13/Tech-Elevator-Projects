package com.techelevator;


<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
=======
import java.io.File;
import java.io.FileNotFoundException;
>>>>>>> e9b55da7735e3ce215fa56760c17a98633a09d1a
import java.util.Scanner;

public class WordCount {
	
	

	public static void main(String[] args) {
		
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("What is the file name? >>>>");
		String filePath = in.nextLine();
		
		File file = new File( filePath );
		String line;
		
		int wordCount = 0;
		int sentenceCount = 0;
<<<<<<< HEAD
		int sentenceCount2 = 0;
		String delimiters = "?!.";
	
		try ( Scanner fileScanner = new Scanner( file)) {
			
			while (fileScanner.hasNextLine() ) { 
			 
=======
		
		try (Scanner fileScanner = new Scanner(file)) {

			while (fileScanner.hasNextLine()) {
>>>>>>> e9b55da7735e3ce215fa56760c17a98633a09d1a
				String nextLineFromFile = fileScanner.nextLine();
					BufferedReader reader = new BufferedReader(new FileReader(file));
				
				try {
					while ((line = reader.readLine()) != null) { // Continue reading until end of file is reached
					    for (int i = 0; i < line.length(); i++) {
					        if (delimiters.indexOf(line.charAt(i)) != -1) { // If the delimiters string contains the character
					            sentenceCount++;
					        }
					    }
					}
				} catch (IOException e) {
				
					e.printStackTrace();
				}
			
				String[] lineFromFileArray = nextLineFromFile.split(" ");
				
				for (int i = 0; i < lineFromFileArray.length; i++) {
					
					if (lineFromFileArray[i].length() >= 1) {
						wordCount++;
<<<<<<< HEAD
						
					} 
					if (lineFromFileArray[i].contains(".") || 
							lineFromFileArray[i].contains("!") || 
								lineFromFileArray[i].contains("?")) {
						
						sentenceCount++;
						
						
					}
				} 
				
			//	wordCount += lineFromFileArray.length;
				//System.out.println(wordCount);
			}
        }      catch (FileNotFoundException ex) {
				//System.out.println(ex.getMessage() );
			
			
			}
			
			System.out.println(wordCount);
			System.out.println(sentenceCount);
			System.out.println(sentenceCount2);
		
		
		

=======
					}
					
					if (lineFromFileArray[i].contains(".") 
							|| lineFromFileArray[i].contains("!")
							|| lineFromFileArray[i].contains("?")) {
						sentenceCount++;
					}
				}

			}
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage() );

		}
		System.out.println("Word Count: " + wordCount);
		System.out.println("Sentence Count: " + sentenceCount);
>>>>>>> e9b55da7735e3ce215fa56760c17a98633a09d1a
	}

	}
