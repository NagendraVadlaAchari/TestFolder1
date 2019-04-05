package com.mits.demo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateChange {

	public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException {
		/*System.out.println("............................");
		
		String a ="2017-10-16 19:19:17.0";
		DateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
		
		 //String dateString="2017-10-16 19:19:17.0";//2014-04-01
		 String dateString="2014-04-01";//2014-04-01
	     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	     Date date=df.parse(dateString);
	     System.out.println("date:"+date);
	     df=new SimpleDateFormat("dd.MM.yy");
	     System.out.println("Formated Date:"+df.format(date));
	     System.out.println("date.getTime"+date.getTime());*/
		
		
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//java.sql.Connection connection = DriverManager.getConnection("jdbc:sqlserver://192.168.1.50:1433/database", "sa", "filenet");
		
		
		java.sql.Connection connection =DriverManager.getConnection("jdbc:sqlserver://192.168.1.50:1433;database=nagendradb;username=sa;password=filenet");
/*
String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
   "databaseName=AdventureWorks;user=MyUserName;password=*****;";
Connection con = DriverManager.getConnection(connectionUrl);*/
		
		
		System.out.println(connection);
		System.out.println(connection+"......connection......");
		System.err.println(connection+"......connection......");	
		
		PreparedStatement preparedStatement=connection.prepareStatement("insert into EmpData values(?,?)");
		preparedStatement.setString(1, "2");
		preparedStatement.setString(2, "Siva");
		
		
		
		int c=preparedStatement.executeUpdate();
System.out.println(c+"....Success....");
		
	}
	
}
