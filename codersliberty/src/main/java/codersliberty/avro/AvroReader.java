package codersliberty.avro;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.FileReader;
import org.apache.avro.file.SeekableInput;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.mapred.FsInput;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Component;

@Component
public class AvroReader {

	public void read(String inputFile) throws IOException {
		Path path = new Path(inputFile);
		Configuration config = new Configuration(); // make this your Hadoop env config
		config.addResource(new Path("/opt/mapr/hadoop/hadoop-2.7.0/etc/hadoop/core-site.xml"));
		config.addResource(new Path("/opt/mapr/hadoop/hadoop-2.7.0/etc/hadoop/hdfs-site.xml"));
		SeekableInput input = new FsInput(path, config);
		DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>();
		FileReader<GenericRecord> fileReader = DataFileReader.openReader(input, reader);
//		for (GenericRecord datum : fileReader) {
//			System.out.println("value = " + datum);
//		}
		while(fileReader.hasNext()) {
			GenericRecord row = fileReader.next();
			Object dt = row.get("username");
			System.out.println(dt);
		}
		fileReader.close();
	}

	public void readWithSchema(Schema schema, File file) throws IOException {
		DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
		DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
		GenericRecord user = null;
		while (dataFileReader.hasNext()) {
			// Reuse user object by passing it to next(). This saves us from
			// allocating and garbage collecting many objects for files with
			// many items.
			user = dataFileReader.next(user);
			System.out.println(user);
		}
	}
	
	public void read1(String schema, String file) throws IOException {
		Schema avs = new Schema.Parser().parse(new File(schema));
		File avr = new File(file);
		readWithSchema(avs, avr); 
	}

}
