package com.recurly.v2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="plan")
public class Plan {

	@XmlElement(name="plan_code") 
	public String 	planCode;
	
	@XmlElement(name="name") 
	public String 	name;
	
	@XmlElement(name="version") 
	public Integer	version;
	
	@XmlElement(name="unit_amount_in_cents")
	public Integer amount;
	
	@XmlElement(name="plan_interval_length") 
	public String 	planIntervalLength;
	
	@XmlElement(name="plan_interval_unit") 
	public String 	planIntervalUnit;
	
	@XmlElement(name="trial_interval_length") 
	public String 	trialIntervalLength;
	
	@XmlElement(name="trial_interval_unit") 
	public String 	trialIntervalUnit;
	
	@XmlElement(name="setup_fee_in_cents") 
	public String 	setUpFee;
	
	@XmlElement(name="accounting_code") 
	public String 	accountingCode;
	
	@XmlElement(name="created_at") 
	public String 	createdAt;
	
}