package lab02_01.logic;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import junitparams.JUnitParamsRunner;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnitParamsRunner.class)
@PrepareForTest({Apartment.class, Logic.class})
public class LogicTest {
	
	@Test
	public void testLogic() throws Exception {
		Logic logic = new Logic();
		
		Date mockDate = Mockito.mock(Date.class);
		Calendar mockCalendar = PowerMockito.mock(Calendar.class);
		PowerMockito.doReturn(mockDate).when(mockCalendar).getTime();
		
		PowerMockito.mockStatic(Calendar.class);
		PowerMockito.when(Calendar.getInstance()).thenReturn(mockCalendar);
		
		Apartment mockApartment = Mockito.mock(Apartment.class);
		PowerMockito.whenNew(Apartment.class).withAnyArguments().thenReturn(mockApartment);
		

		logic.add(mockApartment);
		assertThat(logic.apartments.size()).isEqualTo(1);
		logic.remove(0);
		assertThat(logic.apartments.size()).isEqualTo(0);
		logic.add(0, null, 0);
		assertThat(logic.apartments.size()).isEqualTo(1);
		Apartment a = logic.getApartment(0);
		assertThat(a).isEqualTo(mockApartment);
		logic.edit(0, null, 0);
		String[] s = logic.get(0);
		assertThat(s).isEqualTo(mockApartment.value());
		logic.rent(0, null, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, null, null);
		logic.pay(0, 0);
		logic.free(0);
		logic.delete(0);
		logic.editView();
		
	}
	
	@Test
	public void testApartments() throws Exception{
		Apartment apartment;
		Date mockDate = Mockito.mock(Date.class);
		Calendar mockCalendar = PowerMockito.mock(Calendar.class);
		PowerMockito.doReturn(mockDate).when(mockCalendar).getTime();
		
		PowerMockito.mockStatic(Calendar.class);
		PowerMockito.when(Calendar.getInstance()).thenReturn(mockCalendar);
		
		apartment = new Apartment(1);
		apartment = new Apartment(1, null, 0.0f);
		apartment = new Apartment(1, null, null, 0.0f, 0.0f, 0.0f, mockCalendar, mockCalendar, mockCalendar, false, null, null); 
		
		assertThat(apartment.value()).isNotNull();
		assertThat(apartment.value2()).isNotNull();
		
		apartment.edit(1, null, 0.0f);
		assertThat(apartment.rent(null, 0.0f, 0.0f, mockCalendar, mockCalendar, mockCalendar, null, null)).isEqualTo(true);
		apartment.setAgreement(null);
		apartment.setImgPath(null);
		assertThat(apartment.pay(-1.0f)).isNotNull();
		assertThat(apartment.pay(1.0f)).isNotNull();
		apartment.free();
	}
}