package com.vehicle.rental.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Vehicle {
	private String vehicleId;
    private VehicleType vehicleType;
    private Branch branch;
    private double rentalPrice;
    private Set<LocalDateTime> bookedSlots = new HashSet<>();
    

    public Vehicle(String vehicleId, VehicleType vehicleType, Branch branch, Double rentalPrice){
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.branch = branch;
        this.rentalPrice = rentalPrice;
    }
    
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public double getRentalPrice() {
		return rentalPrice;
	}
	public void setRentalPrice(double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}
	public Set<LocalDateTime> getBookedSlots() {
		return bookedSlots;
	}
	public void setBookedSlots(Set<LocalDateTime> bookedSlots) {
		this.bookedSlots = bookedSlots;
	}

	@Override
    public String toString(){
        return vehicleId + " " + vehicleType + " "  + branch.getName() + " " + rentalPrice;
    }

}
