package com.recurly.v2;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "charge")
public class Charge {
	@XmlElement(name = "amount_in_cents")
	public Integer amount_in_cents;

	@XmlElement(name = "description")
	public String description;

	@XmlElement(name = "id")
	public String id;

	@XmlElement(name = "account_code")
	public String account_code;

	@XmlElement(name = "start_date")
	public Date start_date;

	@XmlElement(name = "end_date")
	public Date end_date;

	@XmlElement(name = "created_at")
	public Date created_at;

 
}
