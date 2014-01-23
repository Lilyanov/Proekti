/* All copyrights reserved, by Yavor Lilyanov */

package com.skrill.interns.DealershipExample;

import java.util.ArrayList;
import java.util.List;

import com.skrill.interns.DesignPatterns.Vehicle;

public class Filter {
	
	public static List<Vehicle> byBrand(List<Vehicle> vehicleList, String brand) {
		List<Vehicle> resultList = new ArrayList<Vehicle>();
		
		for(int i = 0; i < vehicleList.size(); i++) {
			Vehicle currentVehicle = vehicleList.get(i);
			
			if(currentVehicle.getBrand().equalsIgnoreCase(brand)) {
				resultList.add(currentVehicle);
			}
		}
		return resultList;
	}
	
	public static List<Vehicle> byYear(List<Vehicle> vehicleList, int year) {
		List<Vehicle> resultList = new ArrayList<Vehicle>();
		
		for(int i = 0; i < vehicleList.size(); i++) {
			Vehicle currentVehicle = vehicleList.get(i);
			
			if(currentVehicle.getYear() == year) {
				resultList.add(currentVehicle);
			}
		}
		return resultList;
	}
	
	public static List<Vehicle> bySeats(List<Vehicle> vehicleList, int seats) {
		List<Vehicle> resultList = new ArrayList<Vehicle>();
		
		for(int i = 0; i < vehicleList.size(); i++) {
			Vehicle currentVehicle = vehicleList.get(i);
			
			if(currentVehicle.getSeats() == seats) {
				resultList.add(currentVehicle);
			}
		}
		return resultList;
	}
	
	public static List<Vehicle> byPrice(List<Vehicle> vehicleList, int maxPrice) {
		List<Vehicle> resultList = new ArrayList<Vehicle>();
		
		for(int i = 0; i < vehicleList.size(); i++) {
			Vehicle currentVehicle = vehicleList.get(i);
			
			if(currentVehicle.getPrice() <= maxPrice) {
				resultList.add(currentVehicle);
			}
		}
		return resultList;
	}
	
	public static List<Integer> getIds(List<Vehicle> vehicles) {
		List<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i < vehicles.size(); i++) {
			result.add(vehicles.get(i).getId());
		}
		return result;
	}
}
