package Project1.dataProvider;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class dataMember {
	
	public List<HashMap<String, String>> getDataToRead() throws IOException {
	
		//Read json to String		
		String jsonFile =  FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\Project1\\dataProvider\\dataProvider.json"), 
				StandardCharsets.UTF_8);
			
		//String to Hashmap Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonFile, new TypeReference<List<HashMap<String, String>>>(){
			});
		return data;
		
	}
}
