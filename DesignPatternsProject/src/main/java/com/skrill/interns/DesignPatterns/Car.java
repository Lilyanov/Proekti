/* All copyrights reserved, by Yavor Lilyanov */

package com.skrill.interns.DesignPatterns;


public class Car extends Vehicle {

	private double speed;

	public Car(int id, String brand, double price, int year, byte seats, int speed) {
		super(id, brand, price, year, seats);
		this.speed = speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}

	public String toString() {
		
		return new StringBuilder().append("\t|-----------------------|\n")
								  .append("\t|\tCar Id: ").append(id).append("\t|\n")
								  .append("\t|-----------------------|\n")
								  .append("\t|Brand: ").append(brand).append("\t\t|\n")
								  .append("\t|Price: ").append(price).append(" BGN\t|\n")
								  .append("\t|Creation date: ").append(year).append("\t|\n")
								  .append("\t|Seats: ").append(seats).append("\t\t|\n")
								  .append("\t|Max speed: ").append(speed).append(" km\\h\t|\n")
								  .append("\t|_______________________|\n")
								  .toString();
	}

}
