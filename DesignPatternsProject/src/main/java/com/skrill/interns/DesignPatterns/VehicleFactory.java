/* All copyrights reserved, by Yavor Lilyanov */

package com.skrill.interns.DesignPatterns;

import java.io.IOException;
import java.util.List;

public class VehicleFactory {
	
	public static void addVehicle(String vehicle, String[] attributes, List<Vehicle> carList, List<Vehicle> truckList) throws IOException{
		
		byte seats = 0;
		int year = 0;
		double price = 0.0;
		try {
			price = Double.parseDouble(attributes[2]);
			year = Integer.parseInt(attributes[3]);     	// parse year from String to Integer
			seats = Byte.parseByte(attributes[4]);			// parse seats from String to Byte
		}
		catch(NumberFormatException ex) { }// seats = year = price = 0 

		if("car".equalsIgnoreCase(vehicle)) {
			short speed = 0;
			try {
				speed = Short.parseShort(attributes[5]);   		// parse speed from String to Short
			}
			catch(NumberFormatException ex) { }// speed = 0 
			
			carList.add(new Car(carList.size(), attributes[1], price, year, seats, speed));	 // add a new Car to a carList			      	 
		}
		else if("truck".equalsIgnoreCase(vehicle)) {
			short horsePower = 0;
			try {
				horsePower = Short.parseShort(attributes[6]);	// parse horsepower from String to Short
			}
			catch(NumberFormatException ex) { }// horsePower = 0 

			truckList.add(new Truck(carList.size(), attributes[1], price, year, seats, horsePower)); // add a new Truck to a truckList	
		}
		else {
			throw new IOException();
		}
	}
}