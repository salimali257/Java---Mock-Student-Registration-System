/**
 * RegistrationSystem class instantiates and manages a set of graphical user
 * interfaces to handle display and maintenance of student information.
 *
 * CSCE 155 Spring 2008
 * Assignment 6
 * @author Rodrigo Fuzon
 */

import javax.swing.JOptionPane;
import java.util.*;
import java.io.Serializable;
import java.lang.String;
import java.util.Date;

class Graduated extends Students {

	private double lateFee = 10.0;
	private double lateWeeks = 0;
	private double healthFee = 350.0;
	private double reducedFee = .75;	 

 	public void setHealthFee (double healthFee)
	{
		this.healthFee = healthFee;
	}
	
	public double getHealthFee()
	{
		return healthFee;
	}
 	 
/**
 * Sets and get the calculated late fee
 *
 */
	 public void setLateFee(double lateFee)
	 {
	 	this.lateFee = lateFee;
	 }
	 
	 public double getLateFee()
	 {
	 	return lateFee;
	 
	 }
	 
	 
	 
/**
 * Method for getting number of late weeks
 *
 */
 	public void lateWeeks()	
	{

	 
	 	String date = getRegistrationDate().toString();
		
		int day=0;
		int month=0;
		int year=0;
		int lateDays=0;
		int lateWeeks=0;
		day = Integer.parseInt(date.substring(8,9));
		month = Integer.parseInt(date.substring(5,6));
		year = Integer.parseInt(date.substring(0,3));
		GregorianCalendar endDate = new GregorianCalendar(2008, 0, 14);
		GregorianCalendar startDate = new GregorianCalendar(year, month - 1, day);
		Date sDate = startDate.getTime();
		Date eDate = endDate.getTime(); 
		long startDateMS = sDate.getTime();
		long endDateMS = eDate.getTime();
		long elapsedMS = endDateMS - startDateMS;
		long elapsedDays = elapsedMS /(24 * 60 * 60 * 1000);
		
	
		lateWeeks +=(elapsedDays/7) ;
						

	if (elapsedDays % 7 > 0){
		lateWeeks += 1;
		}
	
	if (lateWeeks > 0){
		setLateFee(lateWeeks * lateFee);
		}
	else{
		setLateFee(0 * lateFee);
	}

	} // -- end of get late weeks

}