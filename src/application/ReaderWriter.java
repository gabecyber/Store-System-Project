
package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ReaderWriter{
	
	/*Constructor*/
	public ReaderWriter() {}
	
	/*Method that is responsible for inputting received information into the text file 
	 * provided in the parameters
	 */
	public void ListAdder(String input, File outFile) {
		try {
			//Add information to product list text file. Returns error if fails.
			BufferedWriter fw = new BufferedWriter(new FileWriter(outFile, true)); 
			fw.write(input);
			fw.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void ListRemover(String lineToRemove, File inFile) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(inFile));
			
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.equals(lineToRemove)) continue;
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
				writer.close(); 
				reader.close(); 
				}
	
		catch( IOException e ) {
			System.out.println(e);
		}
	}
		
	
	
	/*Method that iterates and reads through each line of the provided file*/
	public Set<String> ListReader(File inFile) throws IOException {
		Set<String> input = new HashSet<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(inFile));
			String currLine;
			while((currLine = br.readLine()) != null) {
				input.add(currLine);
			}
			br.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return input;
	}
	

}


