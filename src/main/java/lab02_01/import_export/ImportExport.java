package lab02_01.import_export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lab02_01.logic.Apartment;

public class ImportExport {
	private static final String semicolon = ";";
	private static final String nullString = "null";
	private static final SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
	
	
	private static String createCsvContent(Apartment apartment) {
		StringBuilder csvText = new StringBuilder();
		csvText.append(apartment.getId()).append(semicolon);
		csvText.append(apartment.getName()).append(semicolon);
		csvText.append(apartment.getAdress()).append(semicolon);
		csvText.append(apartment.getNominalPrice()).append(semicolon);
		csvText.append(apartment.getRentingPrice()).append(semicolon);
		csvText.append(apartment.getDeposit()).append(semicolon);
		if (apartment.getPaidTo() != null) {
			csvText.append(formatter.format(apartment.getPaidTo().getTime())).append(semicolon);
		} else {
			csvText.append(nullString).append(semicolon);
		}
		if (apartment.getRentedFrom() != null) {
			csvText.append(formatter.format(apartment.getRentedFrom().getTime())).append(semicolon);
		} else {
			csvText.append(nullString).append(semicolon);
		}
		if (apartment.getRentedFrom() != null) {
			csvText.append(formatter.format(apartment.getRentedTo().getTime())).append(semicolon);
		} else {
			csvText.append(nullString).append(semicolon);
		}
		csvText.append(apartment.isFree()).append(semicolon);
		csvText.append(apartment.getAgreement()).append(semicolon);
		csvText.append(apartment.getImgPath()).append(semicolon);	
		
		return csvText.toString();
	}
	
	public static void exportData(Apartment apartment) {
		Date now = Calendar.getInstance().getTime();
		String exportFile = "./export/apartment_".concat(formatter.format(now)).concat(".csv");
		try {
			File exportDirectory = new File("./export/");
			exportDirectory.mkdir();
			FileWriter csvWriter = new FileWriter(exportFile);
			csvWriter.append(createCsvContent(apartment));
			csvWriter.flush();
			csvWriter.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	public static void main(String[] args) {
		Apartment app = new Apartment(146);
		
		exportData(app);
		
		System.out.println("moze dziala?");
	}
}
