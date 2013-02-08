package com.recurly.v2;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="subscription")
public class Subscription {
 
	@XmlElement(name="account") 					
	public Account account;
	
	@XmlElement(name="plan") 					
	public Plan plan;
	
	
	@XmlElement(name="uuid") 					
	public String uuid;
	
	@XmlElement(name="state") 						
	public String state;
	
	@XmlElement(name="unit_amount_in_cents")
	public Integer	unit_amount_in_cents;
	
	@XmlElement(name="currency") 						
	public String currency;
	
	@XmlElement(name="quantity")
	public Integer quantity;
	
	@XmlElement(name="activated_at") 				
	public Date activatedAt;
	
	@XmlElement(name="canceled_at") 				
	public Date canceledAt;
	
	@XmlElement(name="expires_at") 					
	public Date expiresAt;
	
	@XmlElement(name="current_period_started_at") 	
	public Date currentPeriodStartedAt;
	
	@XmlElement(name="current_period_ends_at") 		
	public Date	currentPeriodEndsAt;
	
	@XmlElement(name="trial_started_at") 			
	public Date trialStartedAt;
	
	@XmlElement(name="trial_ends_at") 				
	public Date trialEndsAt;
	
	@XmlElement(name="subscription_add_ons") 					
	public List <SubscriptionAddOns>  subscription_add_ons;
	
	@XmlElement(name="account_code") 				
	public String accountCode;
	
 
	@XmlAttribute(name="cancel")
	public List<String> cancelUrl  ;
	
}