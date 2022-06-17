package appointbooking;

import java.sql.*;
import java.util.*;

public class bookappoint {

	public static void main(String[] args)  throws Exception {
		// TODO Auto-generated method stub
		bookappoint bookp=new bookappoint();
		Scanner select=new Scanner(System.in);
		while(true)
		{
		System.out.println("\n");
		System.out.println("Welcome to our System");
		System.out.println("1.Book Appointment\n 2.Check Appointment\n 3.Update Appointment Date\n 4.Delete Appointment \n 5.Exit");
		int ch=select.nextInt();
		switch(ch)
		{
		case 1: bookp.Insert();
		break;
		case 2: bookp.Read();
		break;
		case 3: bookp.Update();
		break;
		case 4: bookp.Delete();
		break;
		case 5: System.out.print("Thank you for visiting");
		System.exit(0);
		default: System.out.println("Invalid number enter between 1 to 4");
		}
	}
	}
	
	public void Insert() throws Exception {
		
		System.out.println("Connecting to database...");
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadevlop","root","Jishnu@123");
		System.out.println("Connected Successfully");
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter Patient name");
		String pname =sc.nextLine();
		System.out.println("Enter Doctor name");
		String dname =sc.nextLine();
		System.out.println("Enter Patient contact details");
		Long contact =sc.nextLong();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Appointment date");
		String strdate =input.nextLine();
		
		String query = "INSERT INTO appointment(pname,dname,phone,apdate) VALUES (?, ?, ?, ?)";
	    PreparedStatement pstmt = con.prepareStatement(query);
	    
	    pstmt.setString(1, pname);
	    pstmt.setString(2, dname);
	    pstmt.setLong(3, contact);
	    pstmt.setString(4, strdate);
	    
	    int i= pstmt.executeUpdate();
	    
		if(i==1)
		{
			System.out.println("Appointment Booked Successfully");
		}
		else 
		{
			System.out.println("Booking Unsuccessfully");
		}
		con.close();  
	}
	
	public void Read() throws Exception
	{
		System.out.println("Connecting to database...");
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadevlop","root","Jishnu@123");
		System.out.println("Connected Successfully");
		
		Scanner sc1= new Scanner(System.in);
		System.out.println("Enter you mobile number to check appointment");
		Long contact =sc1.nextLong();
		
		Statement pstmt=con.createStatement();
		String query = "Select pname,dname,phone,apdate from appointment where phone ="+contact;
		ResultSet rs=pstmt.executeQuery(query);
		while(rs.next())
		{
			System.out.println("Patient Name = " +rs.getString(1));
			System.out.println("Doctor Name = " +rs.getString(2));
			System.out.println("Patient Number = " +rs.getString(3));
			System.out.println("Appointment Date = " +rs.getString(4));
		}
		System.out.println();
		con.close();  
	}
	
	public void Update() throws Exception
	{
		System.out.println("Connecting to database...");
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadevlop","root","Jishnu@123");
		System.out.println("Connected Successfully");
		
		Scanner sc1= new Scanner(System.in);
		System.out.println("Enter you mobile number to update appointment");
		Long contact =sc1.nextLong();
		Scanner input = new Scanner(System.in);
		System.out.println("Change Appointment date");
		String strdate =input.nextLine();
			
		String query = "Update appointment set apdate='"+strdate+"'where phone="+contact;
		PreparedStatement pstmt = con.prepareStatement(query);
		    
		int u= pstmt.executeUpdate();
		if(u==1)
		{
			System.out.println("Appointment Date updated Successfully");
		}
		else 
		{
			System.out.println("Can't Update Date. \n Please try after sometime.");
		}
		
		Statement st=con.createStatement();
		String query1 = "Select pname,dname,phone,apdate from appointment where phone ="+contact;
		ResultSet rs=st.executeQuery(query1);
		while(rs.next())
		{
			System.out.println("Patient Name = " +rs.getString(1));
			System.out.println("Doctor Name = " +rs.getString(2));
			System.out.println("Patient Number = " +rs.getString(3));
			System.out.println("Appointment Date = " +rs.getString(4));
		}
		con.close();  
	}
	
	public void Delete() throws Exception
	{
		System.out.println("Connecting to database...");
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadevlop","root","Jishnu@123");
		System.out.println("Connected Successfully");
		
		Scanner sc1= new Scanner(System.in);
		System.out.println("Enter you mobile number to delete appointment");
		Long contact =sc1.nextLong();
		
		String query = "Delete from appointment where phone="+contact;
		PreparedStatement pstmt = con.prepareStatement(query);
		int d =pstmt.executeUpdate();
		if(d==1)
		{
			System.out.println("Appointment Deleted Successfully");
		}
		else 
		{
			System.out.println("Deletion Unsuccessfully");
		}
		con.close(); 
	}
	


}
