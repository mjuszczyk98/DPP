package lab02_01.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Logic {
	
	List<Apartment> apartments;
	
	public Logic() {
		this.apartments = new ArrayList<Apartment>();
	}
	
	public boolean add(int id, String address, float nominalPrice) {
		return apartments.add(new Apartment(id, address, nominalPrice));
	}
	
	public boolean delete(int id) {
		return apartments.remove(new Apartment(id));
	}
	
	public void edit(int id, String address, float nominalPrice) {
		apartments.get(apartments.indexOf(new Apartment(id))).edit(address, nominalPrice);;
	}
	
	public void editGet(int id) {
		String[] value = apartments.get(apartments.indexOf(new Apartment(id))).value();
		//TODO return 
	}
	
	public void rent(int id, String name, float rentingPrice, float deposit, 
			int dayFrom, int monthFrom, int yearFrom, int dayTo, int monthTo, int yearTo,
			String agreement, String imgPath) {
		Calendar rentedFrom = new MyCalendar(dayFrom, monthFrom, yearFrom);
		Calendar rentedTo = new MyCalendar(dayTo, monthTo, yearTo);
		apartments.get(apartments.indexOf(new Apartment(id))).rent(name, rentingPrice, deposit, rentedFrom, rentedTo, agreement, imgPath);
	}
	
	public String pay(int id, float amount) {
		if(amount <= 0)
			return null;
		return apartments.get(apartments.indexOf(new Apartment(id))).pay(amount);
	}
	public void free(int id) {
		apartments.get(apartments.indexOf(new Apartment(id))).free();
	}
	
	
	
	public void display() {
		// TODO zwracany typ potrzebny
	}
	
}
