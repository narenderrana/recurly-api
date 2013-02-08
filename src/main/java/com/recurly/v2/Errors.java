package com.recurly.v2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="errors")
public class Errors {


	
	@XmlElement(name="error")
	public List<String> error;
	
	@XmlElement(name="error")
	public List<Message> errorMessage;
	
	
	public List<String> getMessagesList(){
		List list=new ArrayList<String>();
		
		if(error!=null && errorMessage!=null && error.size()==errorMessage.size() ){
			
			for(int i=0;i<errorMessage.size();i++){
		 
			list.add(errorMessage.get(i).field+" "+error.get(i));
			
			}
		}
		
		return list;
	}
	

}
