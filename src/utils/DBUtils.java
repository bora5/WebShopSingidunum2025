package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if (conn == null)
			try {
				conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=webshop_singidunum;user=sa;password=Pa$$w0rd;encrypt=false");
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		return conn;
	}
	
	private DBUtils() {}

}
