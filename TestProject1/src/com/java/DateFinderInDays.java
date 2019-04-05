
package com.java;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateFinderInDays {
	
	public int SLADeadLineCalculation(Date receiveDate, Date endDate) throws Exception{
		int SLADays =0;
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(receiveDate);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(endDate);
		
		System.out.println(cal1.get(Calendar.YEAR)+"...cal1.get(Calendar.YEAR)...");
		System.out.println(cal2.get(Calendar.YEAR)+"...cal2.get(Calendar.YEAR)...");
		
		System.out.println(cal1.get(Calendar.MONTH)+"...cal1.get(Calendar.month)...");
		System.out.println(cal2.get(Calendar.MONTH)+"...cal2.get(Calendar.month)...");
		
		System.out.println(cal1.get(Calendar.DATE)+"...cal1.get(Calendar.DATE)...");
		System.out.println(cal2.get(Calendar.DATE)+"...cal2.get(Calendar.date)...");
		
		if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))
		{
			SLADays=0;
		}
		else
		{
			SLADays = (int)(( (endDate.getTime() - receiveDate.getTime())/ (1000 * 60 * 60 * 24) ))+1;
		}
		    
		
			System.out.println(receiveDate+ "  " +SLADays);
			DateFinderInDays componentMethods = new DateFinderInDays();
			int SLADeadLineDays=componentMethods.businessDayCheck(receiveDate,SLADays);
			System.out.println("SLA Deadline Days ::"+SLADeadLineDays);
			return SLADeadLineDays;
		}



	private int holidayCount=0;


public int businessDayCheck(Date receivedDate,int SLADays) throws Exception{
		SimpleDateFormat dayFormat = new SimpleDateFormat("EEE");

		Date dayPlus=null;
		Calendar c = Calendar.getInstance(); 
		c.setTime(receivedDate);
		boolean isSat=false;
		for (int i=1;i<=SLADays;i++){

			c.add(Calendar.DATE, 1);
			System.out.println("Calendar.DATE::::::::::::"+ Calendar.DATE);

			System.out.println(c.getTime());
			dayPlus=c.getTime();
			
			
			c.setTime(dayPlus);
			String day= dayFormat.format(dayPlus);

			if (day.equalsIgnoreCase("SAT")){
				isSat=true;
				holidayCount=holidayCount+2;
				c.add(c.DATE,2);
				dayPlus=c.getTime();
	
			}
			c.setTime(dayPlus);
			day= dayFormat.format(dayPlus);

			if (isSat==false){
				if (day.equalsIgnoreCase("SUN")){
					holidayCount=holidayCount+1;
					c.add(c.DATE,1);
					dayPlus=c.getTime();
		
				}
			}

		}
		System.out.println(SLADays+holidayCount);
		SLADays=SLADays+holidayCount;
		holidayCount=0;
		return SLADays;
	}


		
	
	public static void main(String[] args) throws Exception {
		
		
		DateFinderInDays componentMethods = new DateFinderInDays();
		
		String startdateStr = "Oct 26 6:30:00 IST 2017";
		DateFormat startformatter = new SimpleDateFormat("MMM dd HH:mm:ss Z yyyy");
		java.util.Date startdate= new Date();
		try {
			startdate = (java.util.Date)startformatter.parse(startdateStr);
			System.out.println(startdate);  
			Calendar cal = Calendar.getInstance();
			cal.setTime(startdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String enddateStr = "Nov 14 00:00:00 IST 2017";
		DateFormat endformatter = new SimpleDateFormat("MMM dd HH:mm:ss Z yyyy");
		java.util.Date enddate=new Date();
		try {
			enddate = (java.util.Date)endformatter.parse(enddateStr);
			System.out.println(enddate);        
			Calendar cal = Calendar.getInstance();
			cal.setTime(enddate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int deadLineCalculation = componentMethods.SLADeadLineCalculation(startdate, enddate);
		System.out.println("deadLineCalculation::::::::::::::::::::::"+ deadLineCalculation );
		
	
		
		
	}

}
