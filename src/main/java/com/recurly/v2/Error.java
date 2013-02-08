package com.recurly.v2;

 

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="error")
public class Error {

	@XmlElement(name="symbol")
	public  String   symbol;	
	
	@XmlElement(name="description")
	public  String   description;	
	
}
