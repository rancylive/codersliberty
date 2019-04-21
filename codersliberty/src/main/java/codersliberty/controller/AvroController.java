package codersliberty.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codersliberty.avro.AvroReader;

@RestController
public class AvroController {

	@Autowired
	private AvroReader reader;
	
	@PostMapping(value="/read")
	public void readFile(@RequestParam String inputFile,@RequestParam String schema) throws IOException {
		reader.read(inputFile);
		//reader.read1(schema, inputFile);
	}
}
