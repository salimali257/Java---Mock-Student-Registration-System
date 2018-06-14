/**
 * RegistrationSystem class instantiates and manages a set of graphical user
 * interfaces to handle display and maintenance of student information.
 *
 * CSCE 155 Spring 2008
 * Assignment 6
 * @author Rodrigo Fuzon
 */
import java.util.*;

public class Undergrad extends Students {
    
 	  /**
     * Store info for Honors for undergrad students.
     */
        private String Honors;

   private double lateFee = .5;
	private double lateWeeks = 0;
	private double healthFee = 350.0;
	private double reducedFee = .5;
	
	public void setHealthFee (double healthFee){
		this.healthFee = healthFee;
	}
	
	public double getHealthFee(){
		return healthFee;
	}
		 
	public void setLateFee(double lateFee){
	 	this.lateFee = lateFee;
	 }
	 	 
	public double getLateFee(){
	 	return lateFee;	 
	 }

	public static void main(String[] args)
	{ System.out.println("This is not the main method, Please run RegistrationSystem.java");
	} // end of main
}