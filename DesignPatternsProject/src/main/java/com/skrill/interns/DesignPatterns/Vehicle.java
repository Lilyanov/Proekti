/* All copyrights reserved, by Yavor Lilyanov */

package com.skrill.interns.DesignPatterns;


public abstract class Vehicle {

	protected int id;
	protected String brand;
	protected double price;
	protected int year;
	protected byte seats;

	public Vehicle(int id, String brand, double price, int year, byte seats) {
		this.id = id;
		this.brand = brand;
		this.price = price;
		this.year = year;
		this.seats = seats;
	}
	

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public void setYear(int date) {
		this.year = date;
	}

	public void setSeats(byte seats) {
		this.seats = seats;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}
	
	public double getPrice() {
		return price;
	}

	public int getYear() {
		return year;
	}

	public byte getSeats() {
		return seats;
	}
	
	public int getId() {
		return id;
	}

	public abstract String toString();
	
}

