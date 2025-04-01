package DataDrivenTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DDT_byProperties {

	public static void main(String[] args) throws IOException  
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\HP\\OneDrive\\Desktop\\AdvanceJavaSelenium\\CommonData_E18.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		System.out.println(BROWSER);
			
		
	}

}
