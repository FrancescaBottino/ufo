package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SightingDAO {
	
	//metodi di accesso al database
	
	
	public List<String> readShapes(){ //elenco delle forme
		
		try {
			Connection conn = DBconnect.getConnection();
			
			String sql="SELECT DISTINCT shape FROM sighting WHERE shape<>'' ORDER BY shape ASC"; //query senza parametri 
			
			
			PreparedStatement st= conn.prepareStatement(sql);
			
			ResultSet res = st.executeQuery(sql);
		
			List <String> formeUFO= new ArrayList<String>();
			
			
			while(res.next()) {
				
				String forma=res.getString("shape");
				
				formeUFO.add(forma);
				
			}
			st.close();
			conn.close();
			return formeUFO;
			
			
		} catch (SQLException e) {
			
			throw new RuntimeException ("Database error in ReadShapes", e); //ribalto la sezione al mio chiamante , generica 
		}
		
		
		
	}
	
	public int countByShape(String shape) {
		
        try{ 
        	
        Connection conn = DBconnect.getConnection();
        	
        String sql2= "SELECT COUNT(*) AS cnt FROM sighting WHERE shape= ? "; //query con parametro
		
		//prepared--> imposto paramentri --> execute 
		
		
		PreparedStatement st2= conn.prepareStatement(sql2); //la connessione è una, uso Prepared per creare statement
		
		//imposto valore parametro
		st2.setString(1,  shape); //primo parametro --> primo ?
		
		
		ResultSet res2= st2.executeQuery(); //nessun parametro perchè sa già cosa mandare
		
		res2.first(); //muove il cursore sulla prima riga (che è l'ultima perchè count ha una unica riga, se no while)
		
		int count= res2.getInt("cnt"); //estraggo dato
		
		
		st2.close();
		
		System.out.println("UFO di forma "+ shape +" sono: "+count);
		
		conn.close();
		
		return count;
        }
          catch (SQLException e) {
			
			throw new RuntimeException("databse error in countByShapes", e); //ribalto la sezione al mio chiamante , generica 
		}
		
		
		
		
	}
	

}
