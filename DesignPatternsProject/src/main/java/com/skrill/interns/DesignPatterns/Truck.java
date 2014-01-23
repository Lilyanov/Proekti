/* All copyrights reserved, by Yavor Lilyanov */

package com.skrill.interns.DesignPatterns;

public class Truck extends Vehicle {
	
	private short horsePower;
	
	public Truck(int id, String brand, double price, int year, byte seats, short horsePower) {
		super(id, brand, price, year, seats);
		this.horsePower = horsePower;
	}


	public void setHorsePower(short horsePower) {
		this.horsePower = horsePower;
	}

	public short getHorsePower() {
		return horsePower;
	}

	public String toString() {
		
		return new StringBuilder().append("\t|-----------------------|\n")
				  				  .append("\t|\tTruck Id: ").append(id).append("\t|\n")
				  				  .append("\t|-----------------------|\n")
				  				  .append("\t|Brand: ").append(brand).append("\t\t|\n")
				  				  .append("\t|Price: ").append(price).append(" BGN\t|\n")
				  				  .append("\t|Creation date: ").append(year).append("\t|\n")
				  				  .append("\t|Seats: ").append(seats).append("\t\t|\n")
				  				  .append("\t|Max speed: ").append(horsePower).append(" km\\h\t|\n")
				  				  .append("\t|_______________________|\n")
				  				  .toString();
	}
	
}
