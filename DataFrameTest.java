import java.io.*;
import java.util.*;

public class DataFrameTest{  	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Please enter a CSV file to load:");
		Scanner scan = new Scanner(System.in);
		String csv = scan.nextLine();
		Scanner reader = new Scanner(new File(csv));//create scanner for reading file
		ArrayList<String[]> data = new ArrayList<String[]>();
		String row = "";
		while(reader.hasNextLine()) {//if there is next line
			row = reader.nextLine();//set row equal to the next line
			String[] temp = row.split(",");
			data.add(temp);
		}
		reader.close();
		String[][] array = new String[data.size()][data.get(0).length];
		data.toArray(array);
		
		
		
		
		DataFrame a = new DataFrame("src//Divvy_Trips_July2013.csv");
		System.out.println(a);
		System.out.println("I(mport) A(verage) +(max) -(min) F(requency) N(ew) Q(uit):");
		String option = scan.nextLine();
		if(option.equalsIgnoreCase("A")) {
			System.out.println("Please enter a columnName to average:");
			String columnName = scan.nextLine();
			if(columnName.equalsIgnoreCase("bikeid")) {
				System.out.println("bikeid average: " + a.average("bikeid"));
			}
			else if(columnName.equalsIgnoreCase("tripduration")) {
				System.out.println("tripduration average: " + a.average("tripduration"));
			}
			else if(columnName.equalsIgnoreCase("from_station_id")) {
				System.out.println("from_station_id average: " + a.average("from_station_id"));
			}
			else if(columnName.equalsIgnoreCase("to_station_id")) {
				System.out.println("to_station_id average: " + a.average("to_station_id"));
			}
			else if(columnName.equalsIgnoreCase("birthyear")) {
				System.out.println("birthyear average: " + a.average("birthyear"));
			}
		}
		else if(option.equalsIgnoreCase("+")) {
			System.out.println("Please enter a columnName to find maximum: ");
			String columnName = scan.nextLine();
			if(columnName.equalsIgnoreCase("bikeid")) {
				System.out.println("bikeid maximum: " + a.maximum("bikeid"));
			}
			else if(columnName.equalsIgnoreCase("tripduration")) {
				System.out.println("tripduration maximum: " + a.maximum("tripduration"));
			}
			else if(columnName.equalsIgnoreCase("from_station_id")) {
				System.out.println("from_station_id maximum: " + a.maximum("from_station_id"));
			}
			else if(columnName.equalsIgnoreCase("to_station_id")) {
				System.out.println("to_station_id maximum: " + a.maximum("to_station_id"));
			}
			else if(columnName.equalsIgnoreCase("birthyear")) {
				System.out.println("birthyear maximum: " + a.maximum("birthyear"));
			}
		}
		else if(option.equalsIgnoreCase("-")) {
			System.out.println("Please enter a columnName to find minimum: ");
			String columnName = scan.nextLine();
			if(columnName.equalsIgnoreCase("bikeid")) {
				System.out.println("bikeid minimum: " + a.minimum("bikeid"));
			}
			else if(columnName.equalsIgnoreCase("tripduration")) {
				System.out.println("tripduration minimum: " + a.minimum("tripduration"));
			}
			else if(columnName.equalsIgnoreCase("from_station_id")) {
				System.out.println("from_station_id minimum: " + a.minimum("from_station_id"));
			}
			else if(columnName.equalsIgnoreCase("to_station_id")) {
				System.out.println("to_station_id minimum: " + a.minimum("to_station_id"));
			}
			else if(columnName.equalsIgnoreCase("birthyear")) {
				System.out.println("birthyear minimum: " + a.minimum("birthyear"));
			}
		}
		if(option.equalsIgnoreCase("F")) {
			System.out.println("Please enter a columnName to generate a frequency chart: ");
			String columnName = scan.nextLine();
			if(columnName.equalsIgnoreCase("bikeid")) {
				System.out.println(a.frequency("bikeid"));
			}
			if(columnName.equalsIgnoreCase("tripduration")) {
				System.out.println(a.frequency("tripduration"));
			}
			if(columnName.equalsIgnoreCase("from_station_id")) {
				System.out.println(a.frequency("from_station_id"));
			}
			if(columnName.equalsIgnoreCase("to_station_id")) {
				System.out.println(a.frequency("to_station_id"));
			}
			if(columnName.equalsIgnoreCase("birthyear")) {
				System.out.println(a.frequency("birthyear"));
			}
		}
		if(option.equalsIgnoreCase("N")) {
			System.out.println("Please enter a columnName to subset on: ");
			String columnName = scan.nextLine();
			if(columnName.equalsIgnoreCase("bikeid")) {
				System.out.println("Please enter an operator: ");
				String operator = scan.nextLine();
				System.out.println("Please enter a value to compare to: ");
				String compare = scan.nextLine();
				System.out.println(a.subset("bikeid",operator,compare));
			}
			if(columnName.equalsIgnoreCase("tripduration")) {
				System.out.println("Please enter an operator: ");
				String operator = scan.nextLine();
				System.out.println("Please enter a value to compare to: ");
				String compare = scan.nextLine();
				System.out.println(a.subset("tripduration",operator,compare));
			}
			if(columnName.equalsIgnoreCase("from_station_id")) {
				System.out.println("Please enter an operator: ");
				String operator = scan.nextLine();
				System.out.println("Please enter a value to compare to: ");
				String compare = scan.nextLine();
				System.out.println(a.subset("from_station_id",operator,compare));
			}
			if(columnName.equalsIgnoreCase("to_station_id")) {
				System.out.println("Please enter an operator: ");
				String operator = scan.nextLine();
				System.out.println("Please enter a value to compare to: ");
				String compare = scan.nextLine();
				System.out.println(a.subset("to_station_id",operator,compare));
			}
			if(columnName.equalsIgnoreCase("birthyear")) {
				System.out.println("Please enter an operator: ");
				String operator = scan.nextLine();
				System.out.println("Please enter a value to compare to: ");
				String compare = scan.nextLine();
				System.out.println(a.subset("birthyear",operator,compare));
			}
		}
		
	}
}