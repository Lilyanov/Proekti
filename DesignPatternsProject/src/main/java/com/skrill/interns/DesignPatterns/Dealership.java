/* All copyrights reserved, by Yavor Lilyanov */

package com.skrill.interns.DesignPatterns;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Dealership {
	
	private String dealershipName;
	private List<Vehicle> carList;
	private List<Vehicle> truckList;
	private CSVReader reader;
	
	public Dealership(String dealershipName) {
		this.dealershipName = dealershipName;
		carList = new ArrayList<Vehicle>();
		truckList = new ArrayList<Vehicle>();
		try {
			reader = new CSVReader(new FileReader("DataBase.csv"));
			getData();
			reader.close();
		} catch (Exception e) {
				System.err.println("DataBase.csv file does not exist or data contained in it is incorrect.The program is terminated");
				e.printStackTrace();
				System.err.println();
				System.exit(0);
		}
	}
	
	private void getData() throws IOException {
		String[] row = reader.readNext(); 										// read the titles - Row[0] - Type;
																				//					 Row[1] - Brand;
		row = reader.readNext();												//					 Row[2] - Price	
															   					//					 Row[3] - Year;
		while(row != null) {													//					 Row[4] - Seats;
			VehicleFactory.addVehicle(row[0], row, carList, truckList);			//					 Row[5] - Speed;
			row = reader.readNext();											//					 Row[6] - Horse Power;
		}
		reader.close();
	}
	
	public void setCarList(List<Vehicle> carList) {
		this.carList = carList;
	}
	
	public void setTruckList(List<Vehicle> truckList) {
		this.truckList = truckList;
	}
	
	public String getName() {
		return dealershipName;
	}
	
	public List<Vehicle> getCarList() {
		return carList;
	}
	
	public List<Vehicle> getTruckList() {
		return truckList;
	}

}
