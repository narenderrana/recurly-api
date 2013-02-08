package com.recurly.v2;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="transactions")
public class Transactions {


	@XmlElement(name="transaction")
	public List <Transaction> transaction;
			
 

}
