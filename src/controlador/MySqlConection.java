package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConection {
	
	public static void connect() {
		String url = "jdbc:mysql://127.0.0.1:3306/?user=root";
		String user = "root";
		String  password = "";
		System.out.println("Conectando...");
		
		try (Connection connection = DriverManager.getConnection(url, user, password)){
			System.out.println("Conectado!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error -> " + e.getMessage());
			e.printStackTrace();
		} 
	}
	

}