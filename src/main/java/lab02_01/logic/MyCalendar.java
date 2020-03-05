package lab02_01.logic;

import java.util.Calendar;

public class MyCalendar extends Calendar{
	
	private static final long serialVersionUID = 1L;

	public MyCalendar(int day, int month, int year) {
		super();
		this.set(year, month, day);
	}

	@Override
	protected void computeTime() {		
	}

	@Override
	protected void computeFields() {
	}

	@Override
	public void add(int field, int amount) {
	}

	@Override
	public void roll(int field, boolean up) {
	}

	@Override
	public int getMinimum(int field) {
		return 0;
	}

	@Override
	public int getMaximum(int field) {
		return 0;
	}

	@Override
	public int getGreatestMinimum(int field) {
		return 0;
	}

	@Override
	public int getLeastMaximum(int field) {
		return 0;
	}
	
}