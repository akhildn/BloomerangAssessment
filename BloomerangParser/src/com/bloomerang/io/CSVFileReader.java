package com.bloomerang.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;

import com.bloomerang.beans.BloomerangRecord;

public class CSVFileReader implements DataReader{

	@Override
	public List<BloomerangRecord> readFileData(String fileLocation
			, String fileName, String mappingName) throws FileNotFoundException {
		
		List<BloomerangRecord> records = new ArrayList<>();
		
		StreamFactory factory = StreamFactory.newInstance();
        factory.load("mappings/"+ mappingName);
        
        InputStream in = new FileInputStream(fileLocation + fileName);
        BeanReader reader = factory.createReader("person-inbound", new InputStreamReader(in));
        Object record = null;
        
        while ((record = reader.read()) != null)
        {
            if (reader.getLineNumber() == 1) {
              continue;
            }
            
            records.add((BloomerangRecord) record);
        }
		return records;
	}

}
