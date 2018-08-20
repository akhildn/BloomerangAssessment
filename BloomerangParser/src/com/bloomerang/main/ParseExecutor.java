package com.bloomerang.main;

import java.io.FileNotFoundException;
import java.util.List;

import com.bloomerang.beans.BloomerangRecord;
import com.bloomerang.io.CSVFileReader;
import com.bloomerang.io.CSVFileWriter;
import com.bloomerang.io.DataReader;
import com.bloomerang.io.DataWriter;
import com.bloomerang.transformation.PersonTransformation;
import com.bloomerang.util.ParserProperties;

public class ParseExecutor {
	
	public static void main(String args[]) throws FileNotFoundException {
		
		DataReader reader = new CSVFileReader();
		DataWriter writer = new CSVFileWriter();
		
		PersonTransformation transformation = new PersonTransformation();
		
		// Reading data from the inbound file
		List<BloomerangRecord> records = reader.readFileData(ParserProperties.dataPath 
				, ParserProperties.INBOUND_FILE_NAME, ParserProperties.INBOUND_MAPPING);
		
		//Applying transformations
		List<BloomerangRecord> outRecords = transformation.transform(records);
		
		// Writing data into output file
		writer.writeFileData(ParserProperties.dataPath, ParserProperties.OUTBOUND_FILE_NAME
				, ParserProperties.OUTBOUND_MAPPING, outRecords);
		
	}
}
