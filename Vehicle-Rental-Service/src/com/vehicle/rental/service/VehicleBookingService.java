package com.vehicle.rental.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vehicle.rental.dao.BranchRepository;
import com.vehicle.rental.dao.CityRepository;
import com.vehicle.rental.model.Branch;
import com.vehicle.rental.model.Vehicle;
import com.vehicle.rental.model.VehicleType;
import com.vehicle.rental.util.Utility;

public class VehicleBookingService {
	
	private static volatile  VehicleBookingService instance;
	private static CityRepository cityRepository = CityRepository.getInstance();
	private static BranchRepository branchRepository = BranchRepository.getInstance();
	
	private VehicleBookingService(){
    }

    public static VehicleBookingService getInstance(){
        if(instance == null) {
        	synchronized (VehicleBookingService.class) {
        		if(instance == null)
        			instance = new VehicleBookingService();
			}
        }
        return instance;
    }
	
	public void addBranch(String city, String branchName) {
		Branch branch = new Branch(branchName);
		cityRepository.addBranch(city, branch);
	}
	
	public void allocatePrice(String city, String branchName, VehicleType vehicleType, Double rentalPrice) {
		for(Branch branch: cityRepository.getBranchesOfCity(city)) {
			if(branch.getName().equalsIgnoreCase(branchName)) {
				branch.getPrice().put(vehicleType, rentalPrice);
				break;
			}
		}
	}
	
	public void addVehicle(String city, String vehicleId, VehicleType vehicleType,  String branchName) {
		Branch currentBranch = null;
		for(Branch branch: cityRepository.getBranchesOfCity(city)) {
			if(branch.getName().equalsIgnoreCase(branchName)) {
				currentBranch = branch;
				break;
			}
		}
		
		if(currentBranch.getPrice().get(vehicleType) == null) {
			System.out.println("NO SUCH VEHICLE TYPE EXIST IN THIS BRANCH");
			return;
		}
		Vehicle vehicle = new Vehicle(vehicleId, vehicleType, currentBranch, currentBranch.getPrice().get(vehicleType));
		branchRepository.addVehicle(currentBranch, vehicle);
	}
	
	public void bookVehicle(String city, VehicleType vehicleType, String startTime, String endTime) {
		List<Branch> branchList = cityRepository.getBranchesOfCity(city);
		List<Vehicle> vehicleListForType = new ArrayList<>();
		for(Branch branch: branchList) {
			List<Vehicle> vehicleList = branchRepository.getVehiclesOfBranch(branch);
			for(Vehicle v: vehicleList) {
				if(v.getVehicleType().equals(vehicleType)) {
					vehicleListForType.add(v);
				}
			}
		}
		Collections.sort(vehicleListForType, Comparator.comparing(Vehicle::getRentalPrice));
		
		for(Vehicle v: vehicleListForType) {
			Set<LocalDateTime> bookSlots = new HashSet<>();
			boolean canAllot = Utility.checkSlotAvailibilty(startTime, endTime, v.getBookedSlots(),
					bookSlots);
			if(canAllot) {
				Utility.bookSlot(v.getBookedSlots(), bookSlots);
				System.out.println(v.getVehicleId() + " from " + v.getBranch().getName() + " booked.");
				return;
			}
		}
		System.out.println("NO " + vehicleType.toString() + " AVAILABLE");
	}
	
	public void viewInventory(String city, String startTime, String endTime) {
		System.out.println();
		List<Branch> branchList = cityRepository.getBranchesOfCity(city);
		for(Branch branch: branchList) {
			System.out.println("Branch: " + branch.getName());
			List<Vehicle> vehicleList = branchRepository.getVehiclesOfBranch(branch);
			for(Vehicle v: vehicleList) {
				boolean canAllot = Utility.checkSlotAvailibilty(startTime, endTime, v.getBookedSlots(),
						new HashSet<>());
				if(canAllot) {
					System.out.println(v.getVehicleType().toString() + " " +  v.getVehicleId() + " Available");
				} else {
					System.out.println(v.getVehicleType().toString() + " " + v.getVehicleId() + " Booked");
				}
			}
		}
	}
	
	
}
