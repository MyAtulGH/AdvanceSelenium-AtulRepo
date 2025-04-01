package DataDrivenTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\HP\\eclipse-workspace\\AdvanceSelenium\\src\\test\\resources\\TestScriptData_E18.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.createSheet("WriteData").createRow(0).createCell(2).setCellValue("Selenium");
		
//		wb.getSheet("DDT").createRow(2).createCell(0).setCellValue(2000);
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\HP\\eclipse-workspace\\AdvanceSelenium\\src\\test\\resources\\TestScriptData_E18.xlsx");
		wb.write(fos);
		wb.close();
		System.out.println("Data written successfully");

	}

}
