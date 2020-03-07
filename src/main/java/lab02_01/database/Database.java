package hello_world;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


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
		                + "    free text\n"
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
		String sql = "INSERT INTO Apartaments(id,name,address,nominalPrice,rentingPrice,deposit,paidTo,rentedFrom,rentedTo,free) VALUES(?,?,?,?,?,?,?,?,?,?)";
		
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
	
	private int [] StringExtracter (String DateString)
	{
		
		String []str = DateString.split("-",3);
		int []date = new int[str.length];
		for(int i=0;i<str.length;i++)
		{
			date[i]=Integer.parseInt(str[i]);
		}
		return date;
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
		    	 date = StringExtracter(str_paidto);
		    	 Calendar paidTo= new MyCalendar(date[2],date[1],date[0]);
		    	 
		    	 String str_rentedFrom = rs.getString("rentedFrom");
		    	 date = StringExtracter(str_rentedFrom);
		    	 Calendar rentedFrom= new MyCalendar(date[2],date[1],date[0]);
		    	 
		    	 String str_rentedTo = rs.getString("rentedTo");
		    	 date = StringExtracter(str_rentedTo);
		    	 Calendar rentedTo= new MyCalendar(date[2],date[1],date[0]);
		
		         boolean free = Boolean.parseBoolean(rs.getString("free"));
		         
		         apartments.add(new Apartment(id,name,address,nominalPrice,rentingPrice,deposit,paidTo,rentedFrom,rentedTo,free));
		         
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
