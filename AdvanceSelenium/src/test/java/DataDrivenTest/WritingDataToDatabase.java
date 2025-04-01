package DataDrivenTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class WritingDataToDatabase {

	public static void main(String[] args) throws SQLException 
	{
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ninza_E18", "root", "hatebreed1234@F");
		
		Statement stmt = conn.createStatement();
		
		int result = stmt.executeUpdate("insert into Ninza_CRM_Details values('safari','http://49.249.28.218:8098/','scott','tiger')");
//		int result = stmt.executeUpdate("update Ninza_CRM_Details set uname='admin' where Browser='firefox'");
//		int result = stmt.executeUpdate("delete from Ninza_CRM_Details where Browser='chrome'");
		
		System.out.println(result);
		
		if(result!=0)
		{
			System.out.println("data stored successfully");
		}
		else
		{
			System.out.println("Operation failed");
		}

		conn.close();
	}

}
