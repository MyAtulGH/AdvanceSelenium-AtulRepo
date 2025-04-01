package DataDrivenTest;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DDT_byJSONfile 
{

	public static void main(String[] args) throws IOException, ParseException 
	{
		JSONParser parser = new JSONParser();
		FileReader file = new FileReader("C:\\Users\\HP\\eclipse-workspace\\AdvanceSelenium\\src\\test\\resources\\Data_E18.json");
		Object javaObj = parser.parse(file);
		
		JSONObject obj = (JSONObject)javaObj;
		String name = obj.get("name").toString();
		String id = obj.get("id").toString();
		String branch = obj.get("Branch").toString();
		String Age = obj.get("Age").toString();
		String isStudent = obj.get("isStudent").toString(); //String
		Object isStudent1 = obj.get("isStudent"); //Object
		
		System.out.println(name);
		System.out.println(id);
		System.out.println(branch);
		System.out.println(Age);
		System.out.println(isStudent); 
		System.out.println(isStudent1); 
		
		
		

	}


}
