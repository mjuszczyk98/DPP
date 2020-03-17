package lab02_01.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * @author Michał Juszczyk
 * 
 * Klasa zajmująca się obsługą operacji dodawania edycji i usuwania apartamentów;
 *
 */
public class Logic {
	
	/**
	 * Lista przechowująca apatamenty
	 */
	public List<Apartment> apartments;
	
	/**
	 * Konstruktor inicjuje liste apartamentów
	 */
	public Logic() {
		this.apartments = new ArrayList<Apartment>();
	}
	
	/**
	 * Funkcja dodający nowy apartament do listy po podaniu zmiennych
	 * @param id - identyfikatow apartamentu
	 * @param address - adres
	 * @param nominalPrice - cena nominalna
	 * @return czy operacja się powiodła
	 */
	public boolean add(int id, String address, float nominalPrice) {
		return apartments.add(new Apartment(id, address, nominalPrice));
	}
	
	/**
	 * Funkcja dodający przekazany apartament do listy 
	 * @param app - apartament dodawany do listy
	 * @return czy operacja się powiodła
	 */
	public boolean add(Apartment app) {
		return apartments.add(app);
	}
	
	/**
	 * Funkcja usuwająca apartament według pozycji
	 * @param position - pozycja usuwanego apartamentu
	 */
	public void remove(int position) {
		apartments.remove(position);
	}
	
	/**
	 * Funkcja usuwająca apartament według id
	 * @param id - identyfikator
	 * @return czy operacja się powiodła
	 */
	public boolean delete(int id) {
		return apartments.remove(new Apartment(id));
	}
	
	/**
	 * Funkcja zwracająca apartament według pozycji
	 * @param position - pozycja na liście
	 * @return apartament
	 */
	public Apartment getApartment(int position) {
		return apartments.get(position);
	}
	
	
	/**
	 * Funkcja edytuje apartament o wskazanym id
	 * @param id - identyfikator
	 * @param address - nowy adres
	 * @param nominalPrice - nowa cena nominalna
	 */
	public void edit(int id, String address, float nominalPrice) {
		apartments.get(apartments.indexOf(new Apartment(id))).edit(id, address, nominalPrice);
	}
	
	/**
	 * Funkcja zwracająca dane apartamentów w tablicy 2 wymiarowej do wyświetlania w GUI
	 * @return tablica dwuwymiarowa apartamentow 
	 */
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
	
	/**
	 * Funkcja zwraca dane apartament według pozycji
	 * @param position - pozycja
	 * @return dane apartamentu w tablicy
	 */
	public String[] get(int position) {
		return apartments.get(position).value();
	}
	
	/** Funkcja modyfikująca dane wynajmu według id
	 * @param id - identyfikator
	 * @param name - nazwa najemcy
	 * @param rentingPrice - czynsz
	 * @param deposit - depozyt
	 * @param dayP - dzien do kiedy oplacony
	 * @param monthP - miesiac do kiedy oplacony
	 * @param yearP - rok do kiedy oplacony
	 * @param dayFrom - dzien wynajmu
	 * @param monthFrom - miesiac wynajmu
	 * @param yearFrom - rok wynajmu
	 * @param dayTo - dzien konca wynajmu
	 * @param monthTo - miesiac konca wynajmu
	 * @param yearTo - rok konca wynajmu
	 * @param agreement - tresc umowy
	 * @param imgPath - sciezka do zdjecia umowy
	 */
	public void rent(int id, String name, float rentingPrice, float deposit, 
			int dayP, int monthP, int yearP, int dayFrom, int monthFrom, int yearFrom, int dayTo, int monthTo, int yearTo,
			String agreement, String imgPath) {
		Calendar rentedFrom = new MyCalendar(dayFrom, monthFrom, yearFrom);
		Calendar rentedTo = new MyCalendar(dayTo, monthTo, yearTo);
		Calendar paidTo = new MyCalendar(dayP, monthP, yearP);
		apartments.get(apartments.indexOf(new Apartment(id))).rent(name, rentingPrice, deposit,paidTo, rentedFrom, rentedTo, agreement, imgPath);
	}
	
	/**
	 * Funkcja przedluzajaca oplacony czas wg id
	 * @param id - identyfikator
	 * @param amount - kwota zapłaty
	 * @return data do kiedy opłacony
	 */
	public String pay(int id, float amount) {
		if(amount <= 0)
			return null;
		return apartments.get(apartments.indexOf(new Apartment(id))).pay(amount);
	}
	
	/**
	 * Funkcja zwalnia apartament
	 * @param id - identyfikator
	 */
	public void free(int id) {
		apartments.get(apartments.indexOf(new Apartment(id))).free();
	}
	
	public void display() {
		// TODO zwracany typ potrzebny
	}
	
}
