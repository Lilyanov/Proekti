/* All copyrights reserved, by Yavor Lilyanov */

package com.skrill.interns.DesignPatterns;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter {
	
	public static List<Vehicle> concatination(Dealership myDealership) {
		
		if(myDealership.getCarList().isEmpty()) {
			return myDealership.getTruckList();
		}
		
		List<Vehicle> compositeList = new ArrayList<Vehicle>();
		
		compositeList.addAll(myDealership.getCarList());
		int numberOfCars = myDealership.getCarList().size();
		
		for(int i = 0; i < myDealership.getTruckList().size(); i++) {
			myDealership.getTruckList().get(i).setId(numberOfCars + i);
		}
		compositeList.addAll(myDealership.getTruckList());
		
		return compositeList;
	}
	
	public static void separate(Dealership myDealership, List<Vehicle> allVehicles) {
		
		List<Vehicle> carList = new ArrayList<Vehicle>();
		List<Vehicle> truckList = new ArrayList<Vehicle>();
		
		int carId = 0, truckId = 0;
		for(int i = 0; i < allVehicles.size(); i++) {
			if(allVehicles.get(i) instanceof Car) {
				allVehicles.get(i).setId(carId);
				carList.add(allVehicles.get(i));
				carId++;
			}
			
			if(allVehicles.get(i) instanceof Truck) {
				allVehicles.get(i).setId(truckId);
				truckList.add(allVehicles.get(i));
				truckId++;
			}
		}
		myDealership.setCarList(carList);
		myDealership.setTruckList(truckList);
	}
}