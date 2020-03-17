package lab02_01.logic;

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
		logic.remove(0);
		logic.add(0, null, 0);
		logic.getApartment(0);
		logic.edit(0, null, 0);
		logic.get(0);
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
		
		apartment.value();
		apartment.value2();
		
		apartment.edit(1, null, 0.0f);
		apartment.rent(null, 0.0f, 0.0f, mockCalendar, mockCalendar, mockCalendar, null, null);
		apartment.setAgreement(null);
		apartment.setImgPath(null);
		apartment.pay(-1.0f);
		apartment.pay(1.0f);
		apartment.free();
	}
}