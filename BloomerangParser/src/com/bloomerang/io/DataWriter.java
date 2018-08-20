package com.bloomerang.io;

import java.io.FileNotFoundException;
import java.util.List;

import com.bloomerang.beans.BloomerangRecord;

public interface DataWriter {

	void writeFileData(String fileLocation, String fileName, String mappingName, List<BloomerangRecord> record)
			throws FileNotFoundException;
}
