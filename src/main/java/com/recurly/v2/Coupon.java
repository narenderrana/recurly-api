package com.recurly.v2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="coupon")
public class Coupon {

	@XmlElement(name="coupon_code") 
	public String 	couponCode;
	
	@XmlElement(name="name") 
	public String 	name;
	
	
 
}