package com.recurly.v2;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 

@XmlRootElement(name="redemptions")
public class  Redemption {
	public static String pluralResourceName = "accounts";
	   @XmlElement(name="coupon")
	    public Coupon coupon;
	    
	    @XmlElement(name="account")
	   	public Account account;
	    
	    @XmlElement(name="single_use")
	    public Boolean single_use;
	    
	    @XmlElement(name="total_discounted_in_cents ")
		public Integer total_discounted_in_cents ;  
	    
	    @XmlElement(name="currency")
		public String currency;
	    @XmlElement(name="state")
		public String transactions;
	    
	    @XmlElement(name="created_at")
	    public Date created_at;
	    
 

    
}