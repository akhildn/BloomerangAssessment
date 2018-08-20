package com.bloomerang.io;

import java.io.FileNotFoundException;
import java.util.List;

import com.bloomerang.beans.BloomerangRecord;

public interface DataReader {

	List<BloomerangRecord> readFileData(String fileLocation, String fileName, String mappingName)
			throws FileNotFoundException;
	
}
