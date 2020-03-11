package lab02_01.import_export;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lab02_01.logic.Apartment;

public class ImportExport {
	private static final String semicolon = ";";
	private static final String nullString = "null";
	private static final SimpleDateFormat formatterFilename = new SimpleDateFormat("ddMMyyyy_HHmmss");
	private static final SimpleDateFormat formatterApartmentData = new SimpleDateFormat("ddMMyyyy");
	
	private static String createCsvContent(Apartment apartment) {
		StringBuilder csvText = new StringBuilder();
		csvText.append(apartment.getId()).append(semicolon);
		csvText.append(apartment.getName()).append(semicolon);
		csvText.append(apartment.getAdress()).append(semicolon);
		csvText.append(apartment.getNominalPrice()).append(semicolon);
		csvText.append(apartment.getRentingPrice()).append(semicolon);
		csvText.append(apartment.getDeposit()).append(semicolon);
		if (apartment.getPaidTo() != null) {
			csvText.append(formatterApartmentData.format(apartment.getPaidTo().getTime())).append(semicolon);
		} else {
			csvText.append(nullString).append(semicolon);
		}
		if (apartment.getRentedFrom() != null) {
			csvText.append(formatterApartmentData.format(apartment.getRentedFrom().getTime())).append(semicolon);
		} else {
			csvText.append(nullString).append(semicolon);
		}
		if (apartment.getRentedFrom() != null) {
			csvText.append(formatterApartmentData.format(apartment.getRentedTo().getTime())).append(semicolon);
		} else {
			csvText.append(nullString).append(semicolon);
		}
		csvText.append(apartment.isFree()).append(semicolon);
		if (apartment.getAgreement().isEmpty() || apartment.getAgreement() == null) {
			csvText.append(nullString).append(semicolon);
		} else {
			csvText.append(apartment.getAgreement()).append(semicolon);
		}
		if (apartment.getImgPath().isEmpty() || apartment.getImgPath() == null) {
			csvText.append(nullString).append(semicolon);
		} else {
			csvText.append(apartment.getImgPath()).append(semicolon);
		}
		
		return csvText.toString();
	}
	
	public static void exportApartment(Apartment apartment, String exportPath) {
		Date now = Calendar.getInstance().getTime();
		String exportFilename = "\\apartment_".concat(formatterFilename.format(now)).concat(".csv");
		try {
			File exportDirectory = new File(exportPath);
			exportDirectory.mkdir();
			FileWriter csvWriter = new FileWriter(exportPath.concat(exportFilename));
			csvWriter.append(createCsvContent(apartment));
			csvWriter.flush();
			csvWriter.close();			
	        System.out.println("Apartment exported successfully to ".concat(exportPath).concat(exportFilename));
		} catch (IOException e) {
		    System.out.println("An error occured during apartment export. Please check stack trace for more information: ");
			e.printStackTrace();
		}		

	}
	
	public static Apartment importApartment(String filepath) {
		Apartment result = null;
		File csvFile = new File(filepath);
		if (csvFile.isFile()) {
			try {
				BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
				String row;
				if ((row = csvReader.readLine()) != null) {
					String[] apartmentData = row.split(";");
					result = createApartmentFromCsvData(apartmentData);
				}
				csvReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		    System.out.println("Apartment imported successfully");
		}
		
		return result;		
	}
	
	private static Apartment createApartmentFromCsvData(String[] csvInput) {
		int id = Integer.parseInt(csvInput[0]);
		String name = csvInput[1];
		String address = csvInput[2];
		float nominalPrice = Float.parseFloat(csvInput[3]);
		float rentingPrice = Float.parseFloat(csvInput[4]);
		float deposit = Float.parseFloat(csvInput[5]);
		Calendar paidTo = parseDate(csvInput[6]);
		Calendar rentedFrom = parseDate(csvInput[7]);
		Calendar rentedTo = parseDate(csvInput[8]);
		boolean free = Boolean.getBoolean(csvInput[9]);
		String agreement = csvInput[10];
		String imgPath = csvInput[11];
		
		return new Apartment(id, name, address, nominalPrice, rentingPrice, deposit, paidTo, rentedFrom, rentedTo, free, agreement, imgPath);
	}
	
	private static Calendar parseDate(String input) {
		Calendar result = null;
		if (input.equals("null")) {
			return null;
		} else {
			result = Calendar.getInstance();
			try {
				result.setTime(formatterApartmentData.parse(input));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
