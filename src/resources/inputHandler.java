package resources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This inputHandler class reads lines from a CSV file and creates an ArrayList from the data.
 * 
 * @author Natasha DeCoste
 * @since 2016-02-28
 * @version 1.0
 * 
 */
public class inputHandler {
	
	private String fileName;
	private ArrayList<String[]> dataset;
	/**
	 * Error called when trying to create without a String parameter
	 */
	inputHandler(){
		System.err.println("File Dataset was not parameterized");
	}

	
	
	
	
	/**
	 * Instantiates the inputHandler object and assigns the private String to the filename.
	 * Returns the ArrayList of Data.
	 * 
	 * @param file	The file name including the extension
	 */
	inputHandler(String file){
		this.fileName = file;
		this.dataset = new ArrayList<String[]>();
		//run();
		

	}
	
	
	
	
	/**
	 *The run function uses try/catch to open the BufferedReader and read from the CSV file.
	 */
	public ArrayList<String[]> run(){
		BufferedReader br = null; 
		String line = "";

		
		try {

			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {

			    // use comma as separator
				String[] flight = line.split(",");
				dataset.add(flight);
				}
			} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			} 
		catch (IOException e) {
			e.printStackTrace();
			} 
		finally {
			if (br != null) {
				try {
					br.close();
					} 
				catch (IOException e) {
					e.printStackTrace();
					}
				}
			}
		return dataset;
	  }	//end of run method
	
	
	/**
	 * To get the number of rows in the dataset.
	 * 
	 * @return an integer that is the amount of rows.
	 */
	private int rows(){
		return dataset.size();
	}
	
	
	
	/**
	 * To get the number of columns in the dataset.
	 * 
	 * @return an integer that is the amount of columns.
	 */
	private int columns(){
		return dataset.get(0).length;
	}
	
	/**
	 * The toString method for the object.
	 * @return 
	 */
	public String toString(){
		String res = "";
		for(int i =0;i<dataset.size();i++){
			System.out.println(Arrays.deepToString(dataset.get(i)));
			
			
		}
		return null;
	}
	
//For testing this class in an isolated way
	/* public static void main(String[] args) {
		 	//inputHandler test = new inputHandler();
		 	inputHandler test2 = new inputHandler("info/2XB3_AirplaneData.csv");
		 	//test.run();
		 	test2.run();
		 	System.out.println(test2.rows());
		 	System.out.println(test2.columns());
		 	System.out.println(test2);
			
		  }*/


}

