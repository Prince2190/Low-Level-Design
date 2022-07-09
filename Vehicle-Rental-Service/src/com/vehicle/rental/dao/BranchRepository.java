package com.vehicle.rental.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vehicle.rental.model.Branch;
import com.vehicle.rental.model.Vehicle;

public class BranchRepository {
	private Map<Branch, List<Vehicle>> vehicleMap;
	private static volatile  BranchRepository instance;

    private BranchRepository(){
    	vehicleMap = new HashMap<>();
    }

    public static BranchRepository getInstance(){
        if(instance == null) {
        	synchronized (CityRepository.class) {
        		if(instance == null)
        			instance = new BranchRepository();
			}
        }
        return instance;
    }
    
    public void addVehicle(Branch branch, Vehicle vehicle){
    	vehicleMap.putIfAbsent(branch, new ArrayList<>());
    	vehicleMap.get(branch).add(vehicle);
    }

    public List<Vehicle> getVehiclesOfBranch(Branch branch){
        List<Vehicle> vehicleList = vehicleMap.get(branch);
        //Collections.sort(vehicleList, Comparator.comparing(Vehicle::getRentalPrice));
        return vehicleList;
    }
}
