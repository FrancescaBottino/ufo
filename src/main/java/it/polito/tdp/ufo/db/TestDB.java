package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDB {
	
	public static void main(String[] args) {
		
		String jdbcURL="jdbc:mysql://localhost/ufo_sightings?user=root&password=nasor819" ;
		
		try {
		Connection conn = DriverManager.getConnection(jdbcURL);
		
		//Statement st=conn.createStatement();
		
		//PRIMO CASO
		
		String sql="SELECT DISTINCT shape FROM sighting WHERE shape<>'' ORDER BY shape ASC"; //query senza parametri 
		
		//prepared --> execute
		
		PreparedStatement st= conn.prepareStatement(sql);
		
		
		ResultSet res = st.executeQuery(sql);
	
		List <String> formeUFO= new ArrayList<String>();
		
		
		while(res.next()) {
			
			String forma=res.getString("shape");
			
			formeUFO.add(forma);
			
		}
		st.close();
		
		System.out.println(formeUFO);
		
		//SECONDO CASO
		
		String sql2= "SELECT COUNT(*) AS cnt FROM sighting WHERE shape= ? "; //query con parametro
		
		//prepared--> imposto paramentri --> execute 
		
		String shapeScelta= "circle";
		
		PreparedStatement st2= conn.prepareStatement(sql2); //la connessione è una, uso Prepared per creare statement
		
		//imposto valore parametro
		st2.setString(1,  shapeScelta); //primo parametro --> primo ?
		
		
		ResultSet res2= st2.executeQuery(); //nessun parametro perchè sa già cosa mandare
		
		res2.first(); //muove il cursore sulla prima riga (che è l'ultima perchè count ha una unica riga, se no while)
		
		int count= res2.getInt("cnt"); //estraggo dato
		
		
		st2.close();
		
		System.out.println("UFO di forma "+ shapeScelta +" sono: "+count);
		
		
		conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
		//DOPO AVER FATTO LA CLASSE SIGHTING DAO
	
		SightingDAO dao= new SightingDAO();
		List<String> formeUFO= dao.readShapes();
		
		for(String forma: formeUFO) {
			int count= dao.countByShape(forma);
			System.out.println("Ufo di forma "+ forma+" sono: "+ count);
			
		}
		
		
	}

}
