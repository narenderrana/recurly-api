package com.recurly.v2;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="subscriptions")
public class Subscriptions {
	
	@XmlElement(name="subscription")
	public List <Subscription> subscriptions;
			
	public String accountCode;

	 
	
}
