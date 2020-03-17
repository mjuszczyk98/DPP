package lab02_01.logic;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import junitparams.JUnitParamsRunner;

public class LogicTest {
	
	@Test
	public void testAdd() throws Exception {
		Logic logic = new Logic();
		Apartment mockApartment = Mockito.mock(Apartment.class);
		
		logic.add(mockApartment);
		assertThat(logic.apartments.size()).isEqualTo(1);
		
		logic = new Logic();
		logic.add(0, null, 0.0f);
		assertThat(logic.apartments.size()).isEqualTo(1);
	}
	
	@Test
	public void testGetApartment() throws Exception {
		Logic logic = new Logic();
		Apartment mockApartment = Mockito.mock(Apartment.class);
		
		logic.add(mockApartment);
		assertThat(logic.getApartment(0)).isEqualTo(mockApartment);
	}

	@Test
	public void testRemove() throws Exception {
		Logic logic = new Logic();
		Apartment mockApartment = Mockito.mock(Apartment.class);
		
		logic.add(mockApartment);
		logic.remove(0);
		assertThat(logic.apartments.size()).isEqualTo(0);
	}
	
	@Test
	public void testDelete() throws Exception {
		Logic logic = new Logic();
		
		logic.add(0, null, 0);
		logic.delete(0);
		assertThat(logic.apartments.size()).isEqualTo(0);
	}
	
	@Test
	public void testEdit() throws Exception {
		Logic logic = new Logic();
		
		logic.add(0, null, 0);
		logic.edit(0, "mock", 1.0f);
		assertThat(logic.apartments.get(0).address).isEqualTo("mock");
	}
	
	@Test
	public void testRent() throws Exception {
		Logic logic = new Logic();
		
		logic.add(0, null, 0);
		logic.rent(0, null, 0.0f, 0.0f, 1, 1, 1, 1, 1, 1, 1, 1, 1, null, null);	
		assertThat(logic.apartments.get(0).free).isEqualTo(false);
	}
	
	@Test
	public void testPay() throws Exception {
		Logic logic = new Logic();
		
		logic.add(0,null,0);
		logic.rent(0, null, 0.0f, 0.0f, 1, 1, 1999, 1, 1, 1999, 1, 10, 1999, null, null);	
		assertThat(logic.pay(0, 1000.0f)).isNotNull();
	}
	
	@Test
	public void testFree() throws Exception {
		Logic logic = new Logic();
		
		logic.add(0,null,0);
		logic.rent(0, null, 0.0f, 0.0f, 1, 1, 1999, 1, 1, 1999, 1, 10, 1999, null, null);	
		logic.free(0);
		assertThat(logic.apartments.get(0).free).isEqualTo(true);
	}
	
	@Test
	public void testEditView() throws Exception {
		Logic logic = new Logic();
		
		logic.add(0,null,0);
		logic.rent(0, null, 0.0f, 0.0f, 1, 1, 1999, 1, 1, 1999, 1, 10, 1999, null, null);	
		assertThat(logic.editView()).isNotNull();
	}
}