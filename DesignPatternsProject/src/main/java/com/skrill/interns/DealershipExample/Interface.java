/* All copyrights reserved, by Yavor Lilyanov */

package com.skrill.interns.DealershipExample;

import java.util.Collections;
import java.util.List;

import com.skrill.interns.DesignPatterns.*;


public class Interface {
	
	private Dealership myDealership;
	
	public Interface(Dealership myDealership) {
		this.myDealership = myDealership;
	}
	
	public void generalMenu() {
		int option;
		System.out.print(new StringBuilder().append("\nWelcome to \"").append(myDealership.getName()).append("\" Dealership!\n")
											.append("\t1.Search cars.\n")
											.append("\t2.Search trucks.\n")
											.append("\t3.Search all.\n")
											.append("\t4.Exit from dealership.\n")
											.toString()
											);
		System.out.print("Select option: ");
		option = Insertions.number(1, 4);
		
		switch(option) {
		case 1: subMenu(myDealership.getCarList(), 'c'); break;
		case 2: subMenu(myDealership.getTruckList(), 't'); break;
		case 3: subMenu(ListAdapter.concatination(myDealership), 'a'); break;
		case 4: { System.out.println("\nThank you for visting my dealership.Bye!");
				  System.exit(0);
				}
		}
	}
	
	private void subMenu(List<Vehicle> vehicles, char type) {
		int option;
	
		System.out.print(new StringBuilder().append("\n\t1.Search by brand.\n")
											.append("\t2.Search by price.\n")
				   							.append("\t3.Search by year.\n")
				   							.append("\t4.Search by seats.\n")
				   							.append("\t5.Show all\n")
				   							.append("\t6.Back to general menu.\n")
				   							.toString()
				   							);
		System.out.print("Select option: ");

		option = Insertions.number(1, 6);
		
		switch(option) {
		case 1: {
					System.out.print("Insert brand: ");
					String filter = Insertions.string();
					subMenu2(Filter.byBrand(vehicles, filter), type);
					break;
		}
		case 2: {
					System.out.print("Insert your max price: ");
					int maxPrice = Insertions.number(1, 999999);
					subMenu2(Filter.byPrice(vehicles, maxPrice), type);
					break;
		}
		case 3: {
					System.out.print("Insert year: ");
					int year = Insertions.number(1500, 2013);
					subMenu2(Filter.byYear(vehicles, year), type);
					break;
		}	
		case 4: {
					System.out.print("Insert seats: ");
					int seats = Insertions.number(2,20);
					subMenu2(Filter.bySeats(vehicles, seats), type);
					break;
		}
		case 5: subMenu2(vehicles, type); break;
		case 6: generalMenu(); break;
		}
		
	}
	
	private  void subMenu2(List<Vehicle> vehicles, char type) {
		int option;
		
		if(vehicles.isEmpty()) {
			System.out.println("\nSorry, but we don't have such vehicle.");
			generalMenu();
			return;
		}
		System.out.println("\n\t     Finded vehicles:");
		System.out.println("*****************************************");
		for(int i = 0; i < vehicles.size(); i++) {
			System.out.println(vehicles.get(i).toString());
		}
		System.out.println("*****************************************\n");
		System.out.print(new StringBuilder().append("\t1.Bye some vehicle.\n")
				  							.append("\t2.Back to general menu.\n")
				  							.toString()
				  							);
		System.out.print("Select option: ");
		option = Insertions.number(1,2);
		switch(option) {
		case 1: {
					System.out.print("Insert the id of the vehicle you want to bye: ");
					int insertedId, lastId;
					List<Vehicle> vehiclesType = null;

					switch(type) {
					case 'c': { vehiclesType = myDealership.getCarList(); break; }
					case 't': vehiclesType = myDealership.getTruckList(); break;
					case 'a': vehiclesType = ListAdapter.concatination(myDealership); break;
					}
					
					lastId = vehiclesType.size() - 1;						 // get the last id of the car in the list
					insertedId = Insertions.number(Filter.getIds(vehicles)); // client insert the id of the car which want to bye
					Collections.swap(vehiclesType, lastId, insertedId); 	 // swap the car with inserted id with last car	
					vehiclesType.get(insertedId).setId(insertedId);			 // 				
					vehiclesType.remove(lastId);							 // remove the vehicle with last id
					
					if(type == 'a') {
						ListAdapter.separate(myDealership, vehiclesType);
					}
					System.out.println("\nCongratulations, you have new vehicle!");
					generalMenu();
					break;
		}
		case 2:	generalMenu(); break;
		}
		
	}
}
