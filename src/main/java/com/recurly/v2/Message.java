package com.recurly.v2;
 
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;
@XmlRootElement(name="error")
public class Message {
	
	@XmlAttribute(name="symbol")
	public  String   symbol;	
	
	@XmlAttribute(name="field")
	public  String   field;	
	
	
	
 
}
