package lab02_01.logic;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Calendar;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

public class ApartmentTest {
	
	
	
	@SuppressWarnings("unused")
	@Test
	public void testNewApartment() throws Exception {
		Apartment apartment;
		
		Calendar mockCalendar = PowerMockito.mock(Calendar.class);
		
		apartment = new Apartment(1);
		apartment = new Apartment(1, null, 0.0f);
		apartment = new Apartment(1, null, null, 0.0f, 0.0f, 0.0f, mockCalendar, mockCalendar, mockCalendar, false, null, null); 
	}
	


	@Test
	public void testValue() throws Exception {
		Calendar mockCalendar = PowerMockito.mock(Calendar.class);
		
		Apartment apartment = new Apartment(1, null, null, 0.0f, 0.0f, 0.0f, mockCalendar, mockCalendar, mockCalendar, false, null, null);
		
		assertThat(apartment.value()).isNotNull();
		assertThat(apartment.value2()).isNotNull();
		 
	}

	@Test
	public void testEdit() throws Exception {
		Apartment apartment = new Apartment(1, null, 0.0f);
		apartment.edit(1, "mock", 0.0f);
		
		assertThat(apartment.address).isEqualTo("mock");
	}
	

	@Test
	public void testRent() throws Exception {
		Calendar mockCalendar = PowerMockito.mock(Calendar.class);
		
		Apartment apartment = new Apartment(1, null, 0.0f);
		assertThat(apartment.rent(null, 0.0f, 0.0f, mockCalendar, mockCalendar, mockCalendar, null, null)).isEqualTo(true);
	}
	
	@Test
	public void testPay() throws Exception {
		Calendar mockCalendar = PowerMockito.mock(Calendar.class);
		
		Apartment apartment = new Apartment(1, null, null, 0.0f, 0.0f, 0.0f, mockCalendar, mockCalendar, mockCalendar, false, null, null);
		
		assertThat(apartment.pay(-1.0f)).isNotNull();
		assertThat(apartment.pay(1.0f)).isNotNull();
	}

	@Test
	public void testFree() throws Exception {
		Calendar mockCalendar = PowerMockito.mock(Calendar.class);
		
		Apartment apartment = new Apartment(1, null, null, 0.0f, 0.0f, 0.0f, mockCalendar, mockCalendar, mockCalendar, false, null, null);
		
		apartment.free();
		assertThat(apartment.free).isEqualTo(true);
	}
	
	
}
