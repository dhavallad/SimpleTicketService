/**
 * 
 */
package com.simpleticket.model;

import java.util.List;

/**
 * @author dhavallad
 *
 */
public class Customer {

	private String FirstName;
	private String LastName;
	private String email;
	public List<SeatHold> reservedSeatList;

	/**
	 * @param Fname
	 * @param Lname
	 * @param email
	 */
	public Customer(String Fname, String Lname, String email) {
		this.FirstName = Fname;
		this.LastName = Lname;
		this.email = email;
	}

	/**
	 * Return the firstName of Customer.
	 * 
	 * @return First Name.
	 */
	public String getFirstName() {
		return FirstName;
	}

	/**
	 * Set the First Name of Customer
	 * 
	 * @param firstName
	 *            the first name of customer.
	 */
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	/**
	 * Return the LastName of Customer.
	 * 
	 * @return LastName
	 */
	public String getLastName() {
		return LastName;
	}

	/**
	 * Set the Last Name of Customer
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		LastName = lastName;
	}

	/**
	 * Return the Email Address of Customer.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the Email Address of Customer.
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
