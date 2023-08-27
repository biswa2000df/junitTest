package junitTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCompare {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		String s="24/june/2021 17:00:18";
	
		String mon="";
		mon=s.substring(3,6);
	
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");

		 //get current date time with Date()
		 Date date = new Date();

		 // Now format the date
		 String date1= dateFormat.format(date);

		 // Print the Date
		 System.out.println("Current date and time is " +date1);
		 System.out.println("Declar date and time is " +s);
		
		 
		 
		
		

		 SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
		 Date date3 = format.parse(s);
		 Date date4 = format.parse(date1);
		 long difference = date4.getTime() - date3.getTime(); 

		 
		 
		 long days_difference = (difference / (1000*60*60*24)) % 365;   
         
         long years_difference = (difference / (1000l*60*60*24*365));   
         
         long seconds_difference = (difference / 1000)% 60;   
          
         long minutes_difference = (difference / (1000*60)) % 60;   
           
         
         long hours_difference = (difference / (1000*60*60)) % 24;   
         
         
         System.out.println("Final Date and Time="+days_difference+"/"+mon+"/"+years_difference+"  "+hours_difference+":"+minutes_difference+":"+seconds_difference);

	}

}
