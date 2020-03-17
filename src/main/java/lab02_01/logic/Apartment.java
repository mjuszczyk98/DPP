package lab02_01.logic;

import java.util.Calendar;

public class Apartment {
	/**
	 * identyfikator
	 */
	int id;
	/**
	 * nazwisko najemcy
	 */
	String name;
	/**
	 * adres
	 */
	String address;
	/**
	 * cena nominalna
	 */
	float nominalPrice;
	/**
	 * cena wynajmu
	 */
	float rentingPrice;
	/**
	 * depozyt
	 */
	float deposit;
	/**
	 * oplacone do
	 */
	Calendar paidTo;
	/**
	 * wynajete od
	 */
	Calendar rentedFrom;
	/**
	 * wynajete do
	 */
	Calendar rentedTo;
	/**
	 * czy wolne
	 */
	boolean free;
	/**
	 * tresc umowy
	 */
	String agreement;
	/**
	 * sciezka do obrazow
	 */
	String imgPath;
	
	/**
	 * Konstruktor tworzący niewynajęty apartament
	 * @param id - identyfikator
	 * @param address - adres
	 * @param nominalPrice - cena nominalna
	 */
	public Apartment(int id, String address, float nominalPrice) {
		this.id = id;
		this.address = address;
		this.nominalPrice = nominalPrice;
		this.free = true;
	}
	
	/**
	 * Konstruktor tworzący wynajęty apartament
	 * @param id - identyfikator
	 * @param name - nazwisko najemcy
	 * @param address - adres
	 * @param nominalPrice - cena nominalna
	 * @param rentingPrice - cena najmu
	 * @param deposit - depozyt
	 * @param paidTo - oplacone do
	 * @param rentedFrom - wynajem od
	 * @param rentedTo - wynajem do
	 * @param free - czy wolny
	 * @param agreement - tresc umowy
	 * @param imgPath - sciezka do umowy
	 */
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

	/**
	 * Konstruktor tworzy pusty apartament
	 * @param id - identyfikator
	 */
	public Apartment (int id) {
		this.id = id;
	}

	/**
	 * Funkcaj edytuje apartament
	 * @param id - identyfikator
	 * @param address - adres
	 * @param nominalPrice - cena nominalna
	 */
	public void edit(int id, String address, float nominalPrice) {
		this.id = id;
		this.address = address;
		this.nominalPrice = nominalPrice;
	}
	
	/**
	 * Funckaja wynajmuje aparatment
	 * @param name - nazwisko najemcy
	 * @param rentingPrice - cena najmu
	 * @param deposit - depozyt
	 * @param paidTo - oplacone do
	 * @param rentedFrom - wynajem od
	 * @param rentedTo - wynajem do
	 * @param agreement - tresc umowy
	 * @param imgPath - sciezka do umowy
	 * @return czy sie udalo
	 */
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

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * Funkcja przedłużająca opłacony czas
	 * @param amount - kwota 
	 * @return do kiedy oplacone
	 */
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
	
	/**
	 * Funkcja zwalniajaca apartament
	 */
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
	
	public String getName() {
		return name;
	}
	
	public String getAdress() {
		return address;
	}
	
	public float getNominalPrice() {
		return nominalPrice;
	}
	
	public float getRentingPrice() {
		return rentingPrice;
	}
	
	public float getDeposit() {
		return deposit;
	}
	
	public Calendar getPaidTo() {
		return paidTo;
	}
	
	public Calendar getRentedFrom() {
		return rentedFrom;
	}
	
	public Calendar getRentedTo() {
		return rentedTo;
	}
	
	public boolean isFree() {
		return free;
	}
	
	public String getAgreement() {
		return agreement;
	}
	
	public String getImgPath() {
		return imgPath;
	}
	
	/**
	 * Funkcaj zwraca wartosci apartamentu w tablicy
	 * @return tablica argumentow
	 */
	public String[] value() {
		String paidToString = Integer.toString(paidTo.get(Calendar.DAY_OF_MONTH))+"-"+
				Integer.toString(paidTo.get(Calendar.MONTH))+"-"+
				Integer.toString(paidTo.get(Calendar.YEAR));
		String rentedFromString = Integer.toString(rentedFrom.get(Calendar.DAY_OF_MONTH))+"-"+
				Integer.toString(rentedFrom.get(Calendar.MONTH))+"-"+
				Integer.toString(rentedFrom.get(Calendar.YEAR));
		String rentedToString = Integer.toString(rentedTo.get(Calendar.DAY_OF_MONTH))+"-"+
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
	
	/**
	 * Funkcaj zwraca wartosci apartamentu w tablicy
	 * @return tablica argumentow
	 */
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
