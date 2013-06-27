package com.recurly.v2;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="accounts")
public class Accounts {

	
	@XmlElement(name="account")
	public List <Account> accounts;
}
