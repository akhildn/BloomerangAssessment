package com.bloomerang.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

import com.bloomerang.beans.BloomerangRecord;
import com.bloomerang.beans.PersonOutLayout;

public class CSVFileWriter implements DataWriter{

	@Override
	public void writeFileData(String fileLocation, String fileName, String mappingName, List<BloomerangRecord> outRecords)
			throws FileNotFoundException {
		
		StreamFactory factory = StreamFactory.newInstance();
        factory.load("mappings/"+ mappingName);
        
        OutputStream outStream = new FileOutputStream(fileLocation + fileName);
        BeanWriter writer  = factory.createWriter("person-outbound", new OutputStreamWriter(outStream));
        
       PersonOutLayout layout = new PersonOutLayout();
       layout.setOutRecords(outRecords);
       writer.write(layout);
       writer.flush();
       writer.close();
		
	}

}
