package com.recurly.v2.utils;

import java.io.StringReader;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import org.omg.CORBA.CTX_RESTRICT_SCOPE;

import com.recurly.v2.Error;
import com.recurly.v2.Errors;
import com.recurly.v2.Messages;
 

public class Response<T>   {

	private static final Logger logger =Logger.getLogger(Response.class);
    private Class type; 
	private boolean  status;
	private StringBuffer data;
	private int code;
	private String description;
	private List<String> errorList=new ArrayList<String>();
	 
	public boolean hasSuccess(){
		
		return status;
	}
	
	
	protected void setType( Class cls ) {
		this.type =cls;
	}


	public int getCode() {
		return code;
	}
	public void setCode( int code ) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription( String description ) {
		this.description = description;
	}
 
 
	public boolean isStatus() {
		return status;
	}
	public void setStatus( boolean status ) {
		this.status = status;
	}
	public StringBuffer getData() {
		return data;
	}
	public void setData(StringBuffer data) {
		this.data = data;
	}
	 
	public T getResponseObject() {

		try {
			
			return  (T)unmarshalling(data,type);
		}
		catch( JAXBException e ) {
			logger.error( "Error in unmarshalling", e);
		}
		catch( Exception e ) {
			logger.error( "Error:", e);
		}
		 
		return null;
	}
 
	
	  
	  public List<String> getErrorList() {
		  
		  Errors errors=null;
		  Error error=null;
		  
		  try {
			  if(data.toString().contains("<errors>")){errors=(Errors)errors(data);}
				 else if(data.toString().contains("<error>")){error=(Error)error(data);}
			}
			catch( JAXBException e ) {
				logger.error( "Error in unmarshalling", e);
			}
		  
		  if(errors!=null)
			  errorList.addAll(errors.getMessagesList()) ;
		  else if(error!=null)
			  errorList.add(error.description);
		  
		return errorList;
	}


	public Object getErrorObject() {
		  
		  try {
			  if(data.toString().contains("<errors>")){return errors(data);}
				 else if(data.toString().contains("<error>")){return error(data);}
			}
			catch( JAXBException e ) {
				logger.error( "Error in unmarshalling", e);
			}
			return null;
	}
	private static Errors errors(StringBuffer errorBuff) throws JAXBException{
			
		    Errors error =  (Errors) unmarshalling( errorBuff, Errors.class );
		    try{
		    	 
				 Messages messages= (Messages)unmarshalling( errorBuff, Messages.class );
				 error.errorMessage=messages.errorMessage;
		    }catch(Exception e){e.printStackTrace();}
		    
		    return error;
	}
	private static Error error(StringBuffer errorBuff) throws JAXBException{
		
	  return (Error)unmarshalling( errorBuff, Error.class );
	}

	private static Object  unmarshalling(StringBuffer buffer,Class className) throws JAXBException{
		  JAXBContext context = JAXBContext.newInstance(className);
		javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
		 return   unmarshaller.unmarshal(new StringReader(buffer.toString()));
	}
}

  abstract class ClassUtils {
	  static public Type[] getGenericType(Class<?> target) {
	    if (target == null)
	      return new Type[0];
	    Type[] types = target.getGenericInterfaces();
	    if (types.length > 0) {
	      return types;
	    }
	    Type type = target.getGenericSuperclass();
	    if (type != null) {
	      if (type instanceof ParameterizedType) {
	        return new Type[] { type };
	      }
	    }
	    return new Type[0];
	  }

	}
