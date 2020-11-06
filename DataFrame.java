import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DataFrame {
	// instance attributes
	String[][] data;	// max number of rows is 25000
	String [] headers, types;  // arrays must be same length
	int numRows, numCols;  // numRows is the actual number of data rows, must be <=25000

	public DataFrame(String [] newHeaders, String [] newTypes) {
		headers=newHeaders;
		types=newTypes;
		numCols=headers.length;
		data = new String[25000][numCols];
	//	numRows=0;
	}
	// constructor for DataFrame with no data yet, just column names and data types
	// arguments assumed to be valid arrays of Strings, and of the same length
	// still need to instantiate the data 2D array to the correct number of columns and 25000 rows

	public DataFrame(String inputFile) throws IOException{
		if (inputFile!=null) {
			File inFile = new File(inputFile);
			Scanner in = new Scanner(inFile);
			headers = in.nextLine().split(",");
			types = in.nextLine().split(",");
			numCols=headers.length;
			data = new String[25000][numCols];
			numRows=0;
			while (in.hasNextLine() && append(in.nextLine())); 
			in.close();
		}
	}
	// constructor for data frame to be loaded from CSV file (comma delimited)
	// first row is column names, second row is data types, assumed to be same length
	// open and read the first 2 rows to instantiate and load the headers and types
	// instantiate the data 2D array to the correct number of columns and 25000 rows
	// read the rest of the rows line by line appending each into the DataFrame

	public boolean append(String row) {
		if (numRows<25000 && row !=null) {
			String [] temp=row.split(",");
			if (temp.length==numCols) {
				data[numRows]=temp;
				numRows++;
				return true;
			}
			else return false;
		}
		else return false;
	}
	// given a comma delimited string, if there is room in the DataFrame and the argument is valid
	// break the argument into individual data fields, check there are the correct number of columns, 
	// add the data to the next available row in the DataFrame and increase the row count.
	// if anything is not correct, return false, else return true	
	

	private int findColumnNumber(String columnName) {
		for (int i =0; i < numCols; i++) {
			if (headers[i].equalsIgnoreCase(columnName)) {
				return i;
			}
		}
		return -1;
	}
	// given a column name, return the column number, or -1 if invalid column name 

	public double average(String columnName) {
		int colNum = findColumnNumber(columnName);
		if (data[1][colNum].equals("int") || data[1][colNum].equals("double") || colNum >= 0) {	
			try {
				int num = 0;
				int totalNum = 0;
				for (int i=0; i < numRows; i++) {
					if (Double.parseDouble(data[i][findColumnNumber(columnName)])>=0) {
						totalNum+=Double.parseDouble(data[i][findColumnNumber(columnName)]);
						num++;
						}
					}	
				return totalNum/num;
	 			}	
			catch(NumberFormatException nfe) {
				return Double.NaN;
			}
		}
		else {
			return Double.NaN;
			}
	}
	
	
		
	// given a column name, if valid column name and if there are rows in the DataFrame and if the 
	// column type is int or double. Traverse that column for all the rows in the DataFrame, converting the String
	// to the correct type, accumulate the values. Then return the average
	// if anything is not correct, return Double.NaN

	public double maximum(String columnName) {
		int colNum = findColumnNumber(columnName);
		if(data[1][colNum].equals("int") || data[1][colNum].equals("double") || colNum >= 0) {
			try {
				double currentMax = 0.0;
				for(int i = 1; i < numRows; i++) {
					if (currentMax < Double.parseDouble(data[i][findColumnNumber(columnName)])) {
						currentMax = Double.parseDouble(data[i][findColumnNumber(columnName)]);
					}
				}
				return currentMax;
			}
			catch(NumberFormatException nfe) {
				return Double.NaN;
			}
		}
		else {
			return Double.NEGATIVE_INFINITY;
		}
		
		
	}
	// given a column name, if valid column name and if there are rows in the DataFrame and if the 
	// column type is int or double. Traverse that column for all the rows in the DataFrame, converting the String
	// to the correct type, and keep track of the maximum value. Then return the maximum.
	// if anything is not correct, return Double.NEGATIVE_INFINITY

	public double minimum(String columnName) {
		int colNum = findColumnNumber(columnName);
		if(data[1][colNum].equals("int") || data[1][colNum].equals("double") || colNum >= 0) {
			try {
				double currentMin = Double.parseDouble(data[0][findColumnNumber(columnName)]);
				for(int i = 1; i < numRows; i++) {
					if(currentMin > Double.parseDouble(data[i][findColumnNumber(columnName)])) {
						currentMin = Double.parseDouble(data[i][findColumnNumber(columnName)]);
					}
				}
				return currentMin;
			}
			catch(NumberFormatException nfe) {
				return Double.NaN;
			}
		}
		else {
			return Double.NEGATIVE_INFINITY;
		}
		
	}
	// given a column name, if valid column name and if there are rows in the DataFrame and if the 
	// column type is int or double. Traverse that column for all the rows in the DataFrame, converting the String
	// to the correct type, and keep track of the minimum. Then return the minimum.
	// if anything is not correct, return Double.POSITIVE_INFINITY
	
	public String frequency(String columnName) {
		int colNum = findColumnNumber(columnName);
		if(data[1][colNum].equals("int") || data[1][colNum].equals("double") || colNum >= 0) {
			try {
				int [] counters = {0, 0, 0, 0, 0};
				double min = minimum(columnName);
				double interval = (maximum(columnName)-min)/4;
				System.out.println(min+interval);
				for(int i = 1; i < numRows; i++) {
					// check each range and increment counters array at the right range
					double val = Double.parseDouble(data[i][findColumnNumber(columnName)]);
					if (val >= min && val < (min+interval)) {
						counters[0]++;
					}
				}
				for(int i = 1; i < numRows; i++) {
					// check each range and increment counters array at the right range
					double val = Double.parseDouble(data[i][findColumnNumber(columnName)]);
					if (val >= (min+interval) && val < (min+2*interval)) {
						counters[1]++;
					}
				}
				for(int i = 1; i < numRows; i++) {
					// check each range and increment counters array at the right range
					double val = Double.parseDouble(data[i][findColumnNumber(columnName)]);
					if (val >= (min+2*interval) && val < (min+3*interval)) {
						counters[2]++;
					}
				}
				for(int i = 1; i < numRows; i++) {
					// check each range and increment counters array at the right range
					double val = Double.parseDouble(data[i][findColumnNumber(columnName)]);
					if (val >= (min+3*interval) && val < (min+4*interval)) {
						counters[3]++;
					}
				}
				for(int i = 1; i < numRows; i++) {
					// check each range and increment counters array at the right range
					double val = Double.parseDouble(data[i][findColumnNumber(columnName)]);
					if (val >= (min+4*interval) && val < (min+5*interval)) {
						counters[4]++;
					}
				}
			
				// format string
				String retStr =  "[" + min + "," + (min+interval) + ")" + " count: " + counters[0] + "\n" +
						"[" + (min+interval) + "," + (min+2*interval) + ")" + " count: " + counters[1]+ "\n" +
						"[" + (min+2*interval) + "," + (min+3*interval) + ")" + " count: " + counters[2]+ "\n" +
						"[" + (min+3*interval) + "," + (min+4*interval) + ")" + " count: " + counters[3]+ "\n" +
						"[" + (min+4*interval) + "," + (min+5*interval) + ")" + " count: " + counters[4];
				
				return retStr;

		}
		catch(NumberFormatException nfe) {
			return "NaN";
		}
		}
		return "";
	}
			
	// given a column name, if valid column name and if there are rows in the DataFrame and if the 
	// column type is int or double. Create an array of counters. Find the maximum and minimum for
	// the given column and calculate the interval for 5 equal ranges. Create an array for the upper
	// limit on each range. Traverse that column for all the rows in the DataFrame, converting the String
	// to the correct type, and increment the counter for the range the data is in. 
	// Format a multi-line String for the 5 ranges and counts for each range. Return the String
	// if anything is not correct, return an empty String

	private boolean checkOperatorValue(String columnData, String operator, String value) {
			double cd = Double.parseDouble(columnData);
			double val = Double.parseDouble(value);
			if(operator.equals("==")) {
				return(cd == val);
			} else if (operator.equals("<")) {
				return (cd < val);
			} else if (operator.equals(">")) {
				return (cd > val);
			} else if(operator.equals("!=")) {
				return (cd != val);
			}

		 else {
			System.out.println(0);
			return false;
		}
	}
	// given 4 Strings - data from a row/column, its type, an operator (== < > !=), and a value
	// convert the data and value a based on the type 
	// compare the data to the value using the operator (and considering its type)
	// return true if the comparison is true, else return false
	// if anything is not correct, return false

	public DataFrame subset(String columnName, String operator, String value) {
	
		int colNum = findColumnNumber(columnName);
		int str = 0;
		String retStr = "";
		if (colNum >= 0 || numRows != 0) {
			try {
				String[] xheader = headers;
				String[] xtype = types;
				// create empty data frame
				DataFrame df = new DataFrame(xheader, xtype);
				for(int i = 0; i < numRows; i++) {
					if(checkOperatorValue(data[i][colNum], operator, value)) {
						retStr += data[i][colNum];
						retStr += ",";
					}
				}
				df.append(retStr);
				System.out.println(retStr);
				return df;
			}
			catch(NumberFormatException nfe) {
				String[] xheader = headers;
				String[] xtype = types;
				DataFrame df = new DataFrame(xheader, xtype);
				return df;
			}
		}
		String[] xheader = headers;
		String[] xtype = types;
		// create empty data frame
		DataFrame df = new DataFrame(xheader, xtype);
		return df;
	}
	// given a column name, if valid column name and if there are rows in the DataFrame
	// Create a new DataFrame with no data yet. Traverse the column for all the rows in the DataFrame,
	// comparing using the given operator and value. If true, create a comma delimited String
	// for that row and append it to the new DataFrame. Once done, return the new DataFrame
	// if anything is not correct, return a null

	public String toString() {
		String temp = "";
		for (int i=0;i<numCols; i++) {
			temp=temp+headers[i];
			if (i<headers.length-1) temp=temp+",";
		}
		return "RowCount="+numRows+" ColumnHeaders="+temp;
	}
	// return a string with the row count and column headers
}







