package com.recurly.v2;

import java.util.List;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="errors")
public class Messages {
	@XmlElement(name="error")
	public List<Message> errorMessage;
}
