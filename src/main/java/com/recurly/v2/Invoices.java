package com.recurly.v2;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "invoices")
public class Invoices  {

 

	@XmlElement(name = "invoice")
	public List<Invoice> invoice;

	public String account_code;
 
}
