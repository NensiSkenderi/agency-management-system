package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class db_connection {

	public static Connection conn;
	private static db_connection instance = new db_connection();
	
	public db_connection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/liberty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
			System.out.println("Not connected to database");
		}
	}
	public static db_connection instance() {
        if (instance == null) {
        	instance = new db_connection();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
	
	
}

