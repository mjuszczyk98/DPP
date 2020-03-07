package lab02_01.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lab02_01.logic.Apartment;
import lab02_01.logic.MyCalendar;


public class Database {
	 
	public static void createNewApartamentTable() {
		
		Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:test.db");
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "CREATE TABLE IF NOT EXISTS Apartaments (\n"
		                + "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
		                + "    name text NOT NULL,\n"
		                + "    address text NOT NULL,\n"
		                + "    nominalPrice text,\n"
		                + "    rentingPrice text,\n"
		                + "    deposit text,\n"
		                + "    paidTo text,\n"
		                + "    rentedFrom text,\n"
		                + "    rentedTo text,\n"
		                + "    free text,\n"
		                + "    agreement text,\n"
		                + "    imgPath text\n"
		                + ")"; 
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Table created successfully");
	   }
	
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
	public void fillDatabase(List<Apartment> apartments)
	{
				
		for(int i=0;i<apartments.size();i++)
		{
		String[] values = apartments.get(i).value();
		String sql = "INSERT INTO Apartaments(id,name,address,nominalPrice,rentingPrice,deposit,"
				+ "paidTo,rentedFrom,rentedTo,free,agreement,imgPath) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setInt(1,Integer.parseInt(values[0]));
        	for(int j = 2,k=1;k<values.length;j++,k++)
        	{
            pstmt.setString(j, values[k]);
        	}
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		}
	}
	
	private int [] stringExtracter (String DateString)
	{
		
		String []str = DateString.split("-",3);
		int []date = new int[str.length];
		for(int i=0;i<str.length;i++)
		{
			date[i]=Integer.parseInt(str[i]);
		}
		return date;
	}
	
	
	public void insertApartment(Apartment apartment)
	{
			String[] values = apartment.value();
			String sql = "INSERT INTO Apartaments(id,name,address,nominalPrice,rentingPrice,deposit,"
					+ "paidTo,rentedFrom,rentedTo,free,agreement,imgPath) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			try (Connection conn = this.connect();
			    PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1,Integer.parseInt(values[0]));
				for(int j = 2,k=1;k<values.length;j++,k++)
				{
			    pstmt.setString(j, values[k]);
				}
			    pstmt.executeUpdate();
			} catch (SQLException e) {
			    System.out.println(e.getMessage());
			}
	}
	
	public Apartment getApartment(int id)
	{
		Apartment apartment=null;
		Connection c = null;
		Statement stmt = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM Apartaments WHERE id ="+id+";" );
		      
		      while ( rs.next() ) {

		    	 
		         int index = rs.getInt("id");
		         String  name = rs.getString("name");
		         String address  = rs.getString("address");
		         float nominalPrice = Float.parseFloat(rs.getString("nominalPrice"));
		         float rentingPrice = Float.parseFloat(rs.getString("rentingPrice"));
		         float deposit = Float.parseFloat(rs.getString("deposit"));
		         
		    	 int []date = new int[3];
		    	 
		    	 String str_paidto = rs.getString("paidTo");
		    	 date = stringExtracter(str_paidto);
		    	 Calendar paidTo= new MyCalendar(date[2],date[1],date[0]);
		    	 
		    	 String str_rentedFrom = rs.getString("rentedFrom");
		    	 date = stringExtracter(str_rentedFrom);
		    	 Calendar rentedFrom= new MyCalendar(date[2],date[1],date[0]);
		    	 
		    	 String str_rentedTo = rs.getString("rentedTo");
		    	 date = stringExtracter(str_rentedTo);
		    	 Calendar rentedTo= new MyCalendar(date[2],date[1],date[0]);
		
		         boolean free = Boolean.parseBoolean(rs.getString("free"));
		         
		         String agreement = rs.getString("agreement");
		         
		         String imgPath = rs.getString("imgPath");
		         
		         apartment = new Apartment(index,name,address,nominalPrice,rentingPrice,deposit,paidTo,rentedFrom,rentedTo,free,agreement,imgPath);
		         
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		   System.out.println("Operation done successfully");
		  
		return apartment;
	}
	
	public void removeApartment(int id)
	{
		Connection c = null;
	    Statement stmt = null;
	    
	    try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql =  "DELETE * FROM Apartaments WHERE id ="+id+";";
         stmt.executeUpdate(sql);
         c.commit();
         stmt.close();
         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }

	}
	
	
	
	public List<Apartment>fillApartmentList()
	{
		List<Apartment> apartments = new ArrayList<Apartment>();
		Connection c = null;
		Statement stmt = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM Apartaments;" );
		      
		      while ( rs.next() ) {

		    	 
		         int id = rs.getInt("id");
		         String  name = rs.getString("name");
		         String address  = rs.getString("address");
		         float nominalPrice = Float.parseFloat(rs.getString("nominalPrice"));
		         float rentingPrice = Float.parseFloat(rs.getString("rentingPrice"));
		         float deposit = Float.parseFloat(rs.getString("deposit"));
		         
		    	 int []date = new int[3];
		    	 
		    	 String str_paidto = rs.getString("paidTo");
		    	 date = stringExtracter(str_paidto);
		    	 Calendar paidTo= new MyCalendar(date[2],date[1],date[0]);
		    	 
		    	 String str_rentedFrom = rs.getString("rentedFrom");
		    	 date = stringExtracter(str_rentedFrom);
		    	 Calendar rentedFrom= new MyCalendar(date[2],date[1],date[0]);
		    	 
		    	 String str_rentedTo = rs.getString("rentedTo");
		    	 date = stringExtracter(str_rentedTo);
		    	 Calendar rentedTo= new MyCalendar(date[2],date[1],date[0]);
		
		         boolean free = Boolean.parseBoolean(rs.getString("free"));
		         
		         String agreement = rs.getString("agreement");
		         
		         String imgPath = rs.getString("imgPath");
		         
		         apartments.add(new Apartment(id,name,address,nominalPrice,rentingPrice,deposit,paidTo,rentedFrom,rentedTo,free,agreement,imgPath));
		         
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		   System.out.println("Operation done successfully");
		  
		return apartments;
	}
}
