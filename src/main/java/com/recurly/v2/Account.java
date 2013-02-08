package com.recurly.v2;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="account")
public class Account  {
	public static String pluralResourceName = "accounts";

	//Required
    @XmlElement(name="account_code")
    public String accountCode;
    @XmlElement(name="state")
   	public String state; 
    
    @XmlElement(name="username")
    public String username;
    
    @XmlElement(name="email")
    public String email;
    
    @XmlElement(name="first_name")
    public String firstName;
    
    @XmlElement(name="last_name")
    public String lastName;
    
  
    
    @XmlElement(name="company_name")
    public String companyName;
    
    
    @XmlElement(name="accept_language")
    public String acceptLanguage;
    
    @XmlElement(name="hosted_login_token")
    public String hostedLoginToken;
    
    @XmlElement(name="created_at")
    public Date createdAt;
    
  
   @XmlElement(name="redemption")
    public Integer redemption;
    
    @XmlElement(name="adjustments")
   	public String adjustments;
    
    @XmlElement(name="billing_info")
    public BillingInfo billingInfo;
    
    @XmlElement(name="invoices")
	public Invoice invoice;  
    
    @XmlElement(name="subscriptions")
	public Subscription subscription;
    
   
    
    @XmlElement(name="transactions")
	public Transaction transactions;
    
   
    
 
}