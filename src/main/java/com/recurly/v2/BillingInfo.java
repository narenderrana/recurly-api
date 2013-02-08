package com.recurly.v2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

 

@XmlRootElement(name="billing_info")
public class BillingInfo {
	 

	
 
	@XmlElement(name="account") 
    public  Account account;

    @XmlElement(name="first_name") 
    public String firstName;

    @XmlElement(name="last_name") 
    public String lastName;
    
    @XmlElement(name="company") 
    public String company;
 
    @XmlElement(name="address1")
    public String address1;

    @XmlElement(name="address2")
    public String address2;
    
    @XmlElement(name="city") 
    public String city;
    
    @XmlElement(name="state")
    public String state;

 
    @XmlElement(name="zip")
    public String zip;

    @XmlElement(name="country")
    public String country;
    
    @XmlElement(name="phone")
    public String phone;
    
    @XmlElement(name="vat_number")
    public String vatNumber;

    @XmlElement(name="ip_address")
    public String ipAddress;

    @XmlElement(name="ip_address_country")
    public String ipAddressCountry;
    
    @XmlElement(name="card_type")
    public String cardType;
    
    @XmlElement(name="year")
    public String year;
    
    @XmlElement(name="month")
    public String month;

    @XmlElement(name="first_six")
    public String firstSix;
    
    @XmlElement(name="last_four")
    public String lastFour;
    
    @XmlElement(name="verification_value")
    public String cvv;
    
    
 
}