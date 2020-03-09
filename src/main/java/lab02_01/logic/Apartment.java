package lab02_01.logic;

import java.util.Calendar;

public class Apartment {
	int id;
	String name;
	String address;
	float nominalPrice;
	float rentingPrice;
	float deposit;
	Calendar paidTo;
	Calendar rentedFrom;
	Calendar rentedTo;
	boolean free;
	String agreement;
	String imgPath;
	
	public Apartment(int id, String address, float nominalPrice) {
		this.id = id;
		this.address = address;
		this.nominalPrice = nominalPrice;
		this.free = true;
	}
	
	public Apartment(int id,String name, String address, float nominalPrice, float rentingPrice, float deposit
			,Calendar paidTo, Calendar rentedFrom, Calendar rentedTo, boolean free, String agreement, String imgPath) {
		this.id = id;
		this.name=name;
		this.address = address;
		this.nominalPrice = nominalPrice;
		this.rentingPrice = rentingPrice;
		this.deposit = deposit;
		this.paidTo = paidTo;
		this.rentedFrom = rentedFrom;
		this.rentedTo = rentedTo;
		this.free = free;
		this.agreement = agreement;
		this.imgPath = imgPath;
	}

	
	public Apartment (int id) {
		this.id = id;
	}

	public void edit(int id, String address, float nominalPrice) {
		this.id = id;
		this.address = address;
		this.nominalPrice = nominalPrice;
	}
	
	public boolean rent(String name, float rentingPrice, float deposit, Calendar paidTo, Calendar rentedFrom, Calendar rentedTo, String agreement, String imgPaht) {
		this.name = name;
		this.rentingPrice = rentingPrice;
		this.deposit = deposit;
		this.rentedFrom = rentedFrom;
		this.paidTo = paidTo;
		this.rentedTo = rentedTo;
		this.agreement = agreement;
		this.imgPath = imgPaht;
		this.free = false;
		
		return true;
	}
	
	public String pay(float amount) {
		int day = paidTo.get(Calendar.DAY_OF_MONTH);
		int month = paidTo.get(Calendar.MONTH);
		int year = paidTo.get(Calendar.YEAR);
		
		int difference;
		int daysInMonth;
		
		difference = (int) (amount / rentingPrice);
		amount -= difference * rentingPrice;
		month += difference;
		
		if(month > 11) {
			month %= 12;
			year += (int) month / 12;
		}
		if(amount > 0) {
		
			switch(month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				daysInMonth = 31;
				break;
			case 2:
				daysInMonth = 28;
				break;
			default:
				daysInMonth = 30;
				break;
			}
			if(year % 4 == 0 && year % 1000 != 0 && month == 2)
				daysInMonth = 31;
			
			day += (int) (amount / (rentingPrice / daysInMonth));
			
			if(day > daysInMonth) {
				day -= daysInMonth;
				month++;
			}
		}
		
		paidTo.set(year, month, day);
		
		if(rentedTo.getTime().before(paidTo.getTime()))
			rentedTo = paidTo;
		
		return Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year);
	}
	
	public void free() {
		name = null;
		rentingPrice = 0;
		deposit = 0;
		paidTo = null;
		rentedFrom = null;
		rentedTo = null;
		free = true;
	}
	
	public int getId() {
		return id;
	}
	
	public String[] value() {
		String paidToString = Integer.toString(paidTo.get(Calendar.DAY_OF_MONTH))+"-"+
				Integer.toString(paidTo.get(Calendar.MONTH))+"-"+
				Integer.toString(paidTo.get(Calendar.YEAR));
		String rentedFromString =Integer.toString(rentedFrom.get(Calendar.DAY_OF_MONTH))+"-"+
				Integer.toString(rentedFrom.get(Calendar.MONTH))+"-"+
				Integer.toString(rentedFrom.get(Calendar.YEAR));
		String rentedToString =Integer.toString(rentedTo.get(Calendar.DAY_OF_MONTH))+"-"+
				Integer.toString(rentedTo.get(Calendar.MONTH))+"-"+
				Integer.toString(rentedTo.get(Calendar.YEAR));

		String[] ret = {
				Integer.toString(id),
				name,
				address,
				Float.toString(nominalPrice),
				Float.toString(rentingPrice),
				Float.toString(deposit),
				paidToString,
				rentedFromString,
				rentedToString,
				Boolean.toString(free),
				agreement,
				imgPath
		};
		return ret;
	}
	public String[] value2() {
		String paidToString = Integer.toString(paidTo.get(Calendar.DAY_OF_MONTH))+"-"+
				Integer.toString(paidTo.get(Calendar.MONTH))+"-"+
				Integer.toString(paidTo.get(Calendar.YEAR));
		String rentedFromString =Integer.toString(rentedFrom.get(Calendar.DAY_OF_MONTH))+"-"+
				Integer.toString(rentedFrom.get(Calendar.MONTH))+"-"+
				Integer.toString(rentedFrom.get(Calendar.YEAR));
		String rentedToString =Integer.toString(rentedTo.get(Calendar.DAY_OF_MONTH))+"-"+
				Integer.toString(rentedTo.get(Calendar.MONTH))+"-"+
				Integer.toString(rentedTo.get(Calendar.YEAR));

		String[] ret = new String[]{
				Integer.toString(id),
				name,
				address,
				Float.toString(nominalPrice),
				Float.toString(rentingPrice),
				Float.toString(deposit),
				paidToString,
				rentedFromString,
				rentedToString,
		};
		return ret;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (this.id == ((Apartment) obj).getId())
			return true;
		else 
			return false;
	}
	
	
	
	
}
