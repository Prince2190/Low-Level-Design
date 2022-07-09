package com.vehicle.rental;

import com.vehicle.rental.model.VehicleType;
import com.vehicle.rental.service.VehicleBookingService;

public class VehicleRentalApp {
	
	private static final String CITY = "Delhi";
	private static VehicleBookingService bookingService = VehicleBookingService.getInstance();
	
	public static void main(String[] args) {
		 
		bookingService.addBranch(CITY, "Vasanth Vihar");
		bookingService.addBranch(CITY, "Cyber City");
		
		bookingService.allocatePrice(CITY, "Vasanth Vihar", VehicleType.SEDAN, 100d);
		bookingService.allocatePrice(CITY, "Vasanth Vihar", VehicleType.HATCHBACK, 80d);
		bookingService.allocatePrice(CITY, "Cyber City", VehicleType.SEDAN, 200d);
		bookingService.allocatePrice(CITY, "Cyber City", VehicleType.HATCHBACK, 50d);
		
		bookingService.addVehicle(CITY, "DL 01 MR 9310", VehicleType.SEDAN, "Vasanth Vihar");
		bookingService.addVehicle(CITY, "DL 01 MR 9311", VehicleType.SEDAN, "Cyber City");
		bookingService.addVehicle(CITY, "DL 01 MR 9312", VehicleType.HATCHBACK, "Cyber City");
		
		bookingService.bookVehicle(CITY, VehicleType.SEDAN, "29-02-2020 10:00 AM", "29-02-2020 01:00 PM");
		bookingService.bookVehicle(CITY, VehicleType.SEDAN, "29-02-2020 02:00 PM", "29-02-2020 03:00 PM");
		bookingService.bookVehicle(CITY, VehicleType.SEDAN, "29-02-2020 02:00 PM", "29-02-2020 03:00 PM");
		bookingService.bookVehicle(CITY, VehicleType.SEDAN, "29-02-2020 02:00 PM", "29-02-2020 03:00 PM");
	
		bookingService.viewInventory(CITY, "29-02-2020 02:00 PM", "29-02-2020 03:00 PM");
		bookingService.viewInventory(CITY, "29-02-2020 04:00 PM", "29-02-2020 05:00 PM");
	}
}
