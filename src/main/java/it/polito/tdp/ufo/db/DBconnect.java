package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {

	//classe per connettere l'URL
	
	public static Connection getConnection() throws SQLException {
		
		String jdbcURL="jdbc:mysql://localhost/ufo_sightings?user=root&password=nasor819" ;
		
		return DriverManager.getConnection(jdbcURL);
		
	}

}
