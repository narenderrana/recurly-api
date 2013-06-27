package com.recurly.v2;


 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.recurly.v2.utils.Response;
 
/**
 * @author narender
 * @version 1.0
 */

public class URLUtils
{

private static final String HTTP_GET="GET";
private static final String HTTP_POST="POST";
private static final String HTTP_DELETE="POST";
private static final String ACCEPT_ApplicationXml="application/xml";
private static final String ACCEPT_ApplicationJson="application/json";
private static final String ACCEPT_ApplicationHtml="text/html";

private static final String CONTENT_TYPE_ApplicationXml="application/xml";
private static final String CONTENT_TYPE_ApplicationJson="application/json";
private static final String CONTENT_TYPE_ApplicationHtml="text/html";

  
/**
 * Logger for this class
 */
private static final  Logger logger =Logger.getLogger(Response.class);
	
	
  public static String addParameter(String URL, String name, String value)
  {
    int qpos = URL.indexOf('?');
    int hpos = URL.indexOf('#');
    char sep = qpos == -1 ? '?' : '&';
    String seg = sep + encodeUrl(name) + '=' + encodeUrl(value);
    return hpos == -1 ? URL + seg : URL.substring(0, hpos) + seg
        + URL.substring(hpos);
  }

  /*
   * */
  public static String encodeUrl(String url)
  {
    try
    {
      return URLEncoder.encode(url, "UTF-8");
    }
    catch (UnsupportedEncodingException uee)
    {
      throw new IllegalArgumentException(uee);
    }
  }
  
 
  
  public static Response doGet(String address,Properties  dataParamMap,Properties  requestHeaderParamMap) throws Exception {
		 
	   
	    address=addRequestParameter(address, dataParamMap);
		logger.info("address:"+address);
	    URL page = new URL(address); 
	    HttpsURLConnection conn = (HttpsURLConnection) page.openConnection();
	    conn.setRequestMethod("GET");
	    conn=addRequestHeader(conn, requestHeaderParamMap);
	    conn.connect(); 
	    return  processResponse(conn);
 
	     
	  }
  
  
  public static Response doPost(String address,Properties  dataParamMap,Properties  requestHeaderParamMap,StringBuffer requestBody) throws Exception {
 
	    address=addRequestParameter(address, dataParamMap);
	    URL page = new URL(address);
	    HttpsURLConnection conn = (HttpsURLConnection) page.openConnection();
	    SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
	    conn.setSSLSocketFactory(sslsocketfactory);
	    conn.setDoOutput(true); 
	    conn=addRequestHeader(conn, requestHeaderParamMap);
			//writing datainto request body
			if(requestBody!=null){
				OutputStream os = conn.getOutputStream();
			    BufferedWriter osw = new BufferedWriter(new OutputStreamWriter(os));
		        osw.write(requestBody.toString());
		        osw.flush();
		        osw.close();

			}	 
	    conn.connect(); 
	    return  processResponse(conn);
	  }
  
  public static Response doPut(String address,Properties  dataParamMap,Properties  requestHeaderParamMap,String xml) throws Exception {
		 
	    address=addRequestParameter(address, dataParamMap);
	    System.out.println("address:"+address);
	    URL page = new URL(address);
	    HttpsURLConnection conn = (HttpsURLConnection) page.openConnection();
	    conn.setRequestMethod("PUT");
	    conn.setDoOutput(true);
	    conn=addRequestHeader(conn, requestHeaderParamMap);
	    if(xml!=null){
			OutputStream os = conn.getOutputStream();
		    BufferedWriter osw = new BufferedWriter(new OutputStreamWriter(os));
	        osw.write(xml.toString());
	        osw.flush();
	        osw.close();

		}
	    return  processResponse(conn);
	  }
  public static Response doDelete(String address,Properties  dataParamMap,Properties  requestHeaderParamMap,String xml) throws Exception {
		 
	    address=addRequestParameter(address, dataParamMap);
	    URL page = new URL(address);
	    HttpsURLConnection conn = (HttpsURLConnection) page.openConnection();
	    conn.setRequestMethod("DELETE");
	    conn.setDoOutput(true);
	    conn=addRequestHeader(conn, requestHeaderParamMap);
	    if(xml!=null){
			OutputStream os = conn.getOutputStream();
		    BufferedWriter osw = new BufferedWriter(new OutputStreamWriter(os));
	        osw.write(xml.toString());
	        osw.flush();
	        osw.close();
		}
	    return  processResponse(conn);
	  }
  
  
  private static Response processResponse(HttpURLConnection conn ){
	  Response response=new Response();
	  InputStreamReader in = null;
	  StringBuffer text=new StringBuffer();

	  try{
	    int responseCode=conn.getResponseCode();
	          response.setCode( responseCode );
	    System.out.println("response code:"+responseCode);
	    if (responseCode >=200 && responseCode<300) {
	    	/*recurly range of success 200-299*/
	    	in = new InputStreamReader(conn.getInputStream()); 
	    	text=processStream( in, text );
	    	response.setStatus( true );
	    	response.setDescription("Request completed successfully.");
	     
	    	}
	    else if(responseCode >=400 && responseCode<500) {
	    	/*400–499 as client request errors*/
	    	in =  new InputStreamReader(conn.getErrorStream());	
	    	text=processStream( in, text );
	    	response.setStatus( false );
	    	response.setDescription("Request failed due to errors in client request.");
	    }
	    else if(responseCode >=500 && responseCode<600) {
	    	/*500–599 as server errors*/
	    	in =  new InputStreamReader(conn.getErrorStream());
	    	text=processStream( in, text );
	    	response.setStatus( false );
	    	response.setDescription("Request failed due to server error.");
	    }
	    
	 
	   
	    in.close();
	    
	  }catch(Exception e){
		  e.printStackTrace();
		  if(in!=null){
			  try{in.close();}catch(Exception e1){in=null;}}
		  
	  }
	     response.setData( text );
	    return response; 
  }
  
   private static StringBuffer  processStream( InputStreamReader in,StringBuffer text ) throws IOException   {
	   
	   if(in!=null){
		    BufferedReader buff = new BufferedReader(in);
		    String line = buff.readLine();
		    while (line != null) {
		    	
		    	logger.debug(line);
		        text.append(line + "\n");
		       
		        line = buff.readLine();
		        
		    } 
		    buff.close();
		    
		    }
	   return text;
   }
  
  
  private static HttpsURLConnection addRequestHeader(HttpsURLConnection conn,Properties headersMap){
	  
	  if(headersMap!=null){
	  Enumeration repnuma=headersMap.keys();
		while(repnuma.hasMoreElements()){
			Object key= repnuma.nextElement();
			conn.addRequestProperty(key.toString(), headersMap.get(key).toString());
			logger.debug("setting header:"+headersMap.get(key).toString());
		} 
	  }
	  return conn;
  }
  private static String addRequestParameter(String  address,Properties dataParamMap){
	  
	  if(dataParamMap!=null){
			 Enumeration enuma=dataParamMap.keys();
				while(enuma.hasMoreElements()){
					Object key= enuma.nextElement();
					address=URLUtils.addParameter(address, key.toString(), dataParamMap.get(key).toString());
				
				}}
	  return address;
  }


}

   
    
    