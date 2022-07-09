package com.vehicle.rental.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Branch {
	private String name;
	private Map<VehicleType, Double> price = new HashMap<>();
	
	public Branch(String name){
        this.name = name;
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<VehicleType, Double> getPrice() {
		return price;
	}
	public void setPrice(Map<VehicleType, Double> price) {
		this.price = price;
	}
	
	@Override
    public String toString(){
        return name;
    }

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branch other = (Branch) obj;
		return Objects.equals(name, other.name);
	}
}
