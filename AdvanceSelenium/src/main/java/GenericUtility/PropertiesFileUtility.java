package GenericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility 
{
	public String readingDataFromPropFile(String Key) throws IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\HP\\OneDrive\\Desktop\\AdvanceJavaSelenium\\CommonData_E18.properties");
	    Properties prop=new Properties();
	    prop.load(fis);
	    String data = prop.getProperty(Key);
	    return data;
	}

}
