package com.vehicle.rental.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Utility {
	
	public static LocalDateTime getLocalDateTime(String dateStr) {
		String format = "dd-MM-yyyy hh:mm a";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.US);
		LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
		return dateTime;
	}
	
	
	/*
	 * public static void main(String[] args) { LocalDateTime date1 =
	 * getLocalDateTime("29-02-2020 10:00 AM"); LocalDateTime date2 =
	 * getLocalDateTime("01-03-2020 01:00 PM");
	 * 
	 * int diff = date2.compareTo(date1); System.out.println(diff);
	 * 
	 * System.out.println(date2.plusHours(1)); }
	 */
	 
	
	public static boolean checkSlotAvailibilty(String start, String end, Set<LocalDateTime> slots,
			Set<LocalDateTime> bookSlots) {
		//System.out.println(start + " " + end);
		LocalDateTime startDateTime = getLocalDateTime(start);
		LocalDateTime endDateTime = getLocalDateTime(end);
		//System.out.println(startDateTime + " " + endDateTime);
		while(!startDateTime.equals(endDateTime)) {
			if(slots.contains(startDateTime)) {
				return false;
			}
			bookSlots.add(startDateTime);
			//System.out.println(startDateTime);
			startDateTime = startDateTime.plusHours(1);
		}
		return true;
	}
	
	public static boolean bookSlot(Set<LocalDateTime> slots, Set<LocalDateTime> bookSlots) {
		slots.addAll(bookSlots);
		return true;
	}
}
