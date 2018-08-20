package com.bloomerang.transformation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bloomerang.beans.BloomerangRecord;
import com.bloomerang.beans.PersonInRecord;
import com.bloomerang.beans.PersonOutRecord;
import com.bloomerang.util.ParserProperties;

public class PersonTransformation {

	public List<BloomerangRecord> transform(List<BloomerangRecord> records) {
		
		List<BloomerangRecord> outRecords = new ArrayList<>();
		
		for(BloomerangRecord record : records) {
			PersonInRecord inRecord = (PersonInRecord) record;
			PersonOutRecord outRecord = new PersonOutRecord();
			
			transformName(inRecord, outRecord);
			
			transformYearOfBirth(inRecord, outRecord);
			
			transformEmail(inRecord, outRecord);
			
			transformPhone(inRecord, outRecord);
			
			outRecords.add(outRecord);
			
		}
		
		return outRecords;
	}

	private void transformPhone(PersonInRecord inRecord, PersonOutRecord outRecord) {
		String phone = inRecord.getPhone().toUpperCase();
		Pattern extnPattern = Pattern.compile(ParserProperties.EXTN_PATTERN);
		String phoneParts[] = extnPattern.split(phone);
		String phoneNum = inRecord.getPhone();
		String extnNum = "";
		
		if(phoneParts.length > 1) {
			phoneNum = phoneParts[0];
			extnNum = phoneParts[1];
		}

		if(inRecord.getContactType().toUpperCase().trim().equals("HOME")) {
			outRecord.setHomePhone(phoneNum.replaceAll("[()\\\\s\\s-]+", ""));
			outRecord.setHomePhoneExtn(extnNum.replaceAll("[()\\\\s\\s-]+", ""));
			outRecord.setWorkPhone("");
			outRecord.setWorkPhoneExtn("");
		} else if(inRecord.getContactType().toUpperCase().trim().equals("WORK")) {
			outRecord.setWorkPhone(phoneNum.replaceAll("[()\\\\s\\s-]+", ""));
			outRecord.setWorkPhoneExtn(extnNum.replaceAll("[()\\\\s\\s-]+", ""));
			outRecord.setHomePhone("");
			outRecord.setHomePhoneExtn("");
		} else {
			outRecord.setWorkPhone("");
			outRecord.setWorkPhoneExtn("");
			outRecord.setHomePhone("");
			outRecord.setHomePhoneExtn("");
		}
	}

	private void transformEmail(PersonInRecord inRecord, PersonOutRecord outRecord) {
		String email = inRecord.getEmail();
		outRecord.setEmail(email);
		outRecord.setEmailDomain(email.substring(email.indexOf("@")+1,email.length()));
	}

	private void transformYearOfBirth(PersonInRecord inRecord, PersonOutRecord outRecord) {
		SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
		String yearOfBirth = null;
		try {
			Date date = formatter.parse(inRecord.getDob());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			yearOfBirth = String.valueOf(calendar.get(Calendar.YEAR));
			
		} catch (ParseException e) {
			System.out.println("Date Parse Error: " + inRecord.toString());
			System.out.println("Date Parse Error: " + e.getMessage());
		}
		
		outRecord.setYearOfBirth(yearOfBirth);
	}

	private void transformName(PersonInRecord inRecord, PersonOutRecord outRecord) {
		
		
		Pattern titlePattern = Pattern.compile(ParserProperties.TITLE_PATTERN);
		Matcher titleMatch = titlePattern.matcher(inRecord.getName());
		
		String titles = "";
		while(titleMatch.find()) 
			titles += titleMatch.group().trim();
		
		Pattern suffixPattern = Pattern.compile(ParserProperties.SUFFIX_PATTERN);
		Matcher suffixMatch = suffixPattern.matcher(inRecord.getName());
		
		String suffix = "";
		while(suffixMatch.find()) 
			suffix += suffixMatch.group().trim();
		
		Pattern fullNamePattern = Pattern.compile(ParserProperties.TITLE_PATTERN 
				+ ParserProperties.SUFFIX_PATTERN
				+ ParserProperties.EXCLUDE_PATTERN);
		
		Matcher fullMatch = fullNamePattern.matcher(inRecord.getName());
		
		String fullName = fullMatch.replaceAll("").trim();
		String nameParts[] = fullName.split(" ");
		
		outRecord.setFirstName(nameParts[0]);
		outRecord.setLastName(nameParts[nameParts.length - 1]);
		outRecord.setTitle(titles);
		outRecord.setSuffix(suffix);
	}

}
