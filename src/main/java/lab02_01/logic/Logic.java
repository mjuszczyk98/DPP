package lab02_01.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Logic {
	
	public List<Apartment> apartments;
	
	public Logic() {
		this.apartments = new ArrayList<Apartment>();
	}
	
	public boolean add(int id, String address, float nominalPrice) {
		return apartments.add(new Apartment(id, address, nominalPrice));
	}
	
	public boolean add(Apartment app) {
		return apartments.add(app);
	}
	
	public void remove(int position) {
		apartments.remove(position);
	}
	
	public boolean delete(int id) {
		return apartments.remove(new Apartment(id));
	}
	
	public Apartment getApartment(int position) {
		return apartments.get(position);
	}
	
	public void edit(int id, String address, float nominalPrice) {
		apartments.get(apartments.indexOf(new Apartment(id))).edit(id, address, nominalPrice);
	}
	
	public String[][] editView() {
		String[][] ret = new String[apartments.size()][9];
		for(int i = 0; i < apartments.size(); i++) {
			String[] a = apartments.get(i).value2();
			for(int j = 0; j < 9; j++) {
				ret[i][j] = a[j];
			}
		}
		return ret;
	}
	
	public String[] get(int position) {
		return apartments.get(position).value();
	}
	
	public void rent(int id, String name, float rentingPrice, float deposit, 
			int dayP, int monthP, int yearP, int dayFrom, int monthFrom, int yearFrom, int dayTo, int monthTo, int yearTo,
			String agreement, String imgPath) {
		Calendar rentedFrom = new MyCalendar(dayFrom, monthFrom, yearFrom);
		Calendar rentedTo = new MyCalendar(dayTo, monthTo, yearTo);
		Calendar paidTo = new MyCalendar(dayP, monthP, yearP);
		apartments.get(apartments.indexOf(new Apartment(id))).rent(name, rentingPrice, deposit,paidTo, rentedFrom, rentedTo, agreement, imgPath);
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
