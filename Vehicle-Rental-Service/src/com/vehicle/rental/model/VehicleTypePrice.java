package com.vehicle.rental.model;

public class VehicleTypePrice {
	VehicleType vehicleType;
	Double price;
	
	public VehicleTypePrice(VehicleType vehicleType, Double price) {
		super();
		this.vehicleType = vehicleType;
		this.price = price;
	}
	
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
