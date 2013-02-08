package com.recurly.v2;


 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
private static final Logger logger = LoggerFactory.getLogger(URLUtils.class);
	
	
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
  
 
  
  public static StringBuffer doGet(String address,Properties  dataParamMap,Properties  requestParamMap) throws Exception {
		 
 
	    address=addRequestParameter(address, dataParamMap);
		logger.info("address:"+address);
	    URL page = new URL(address); 
	    HttpsURLConnection conn = (HttpsURLConnection) page.openConnection();
	    conn.setRequestMethod("GET");
	    conn=addRequestHeader(conn, requestParamMap);
	    conn.connect(); 
	    StringBuffer text =processResponse(conn);
	    return text;
	  }
  
  
  public static StringBuffer doPost(String address,Properties  dataParamMap,Properties  requestParamMap,StringBuffer requestBody) throws Exception {
 
	    address=addRequestParameter(address, dataParamMap);
	    URL page = new URL(address);
	    HttpsURLConnection conn = (HttpsURLConnection) page.openConnection();
	    SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
	    conn.setSSLSocketFactory(sslsocketfactory);
	    conn.setDoOutput(true); 
	    conn=addRequestHeader(conn, requestParamMap);
			//writing datainto request body
			if(requestBody!=null){
				OutputStream os = conn.getOutputStream();
			    BufferedWriter osw = new BufferedWriter(new OutputStreamWriter(os));
		        osw.write(requestBody.toString());
		        osw.flush();
		        osw.close();

			}	 
	    conn.connect(); 
	    StringBuffer text=processResponse(conn);
 
	     return text;
	  }
  
  public static StringBuffer doPut(String address,Properties  dataParamMap,Properties  requestParamMap,String xml) throws Exception {
		 
	    address=addRequestParameter(address, dataParamMap);
	    System.out.println("address:"+address);
	    URL page = new URL(address);
	    HttpsURLConnection conn = (HttpsURLConnection) page.openConnection();
	    conn.setRequestMethod("PUT");
	    conn.setDoOutput(true);
	    conn=addRequestHeader(conn, requestParamMap);
	    if(xml!=null){
			OutputStream os = conn.getOutputStream();
		    BufferedWriter osw = new BufferedWriter(new OutputStreamWriter(os));
	        osw.write(xml.toString());
	        osw.flush();
	        osw.close();

		}
	    conn.connect(); 
	    StringBuffer text=processResponse(conn);
 
 
	     return text;
	  }
  public static StringBuffer doDelete(String address,Properties  dataParamMap,Properties  requestParamMap,String xml) throws Exception {
		 
	    address=addRequestParameter(address, dataParamMap);
	    URL page = new URL(address);
	    HttpsURLConnection conn = (HttpsURLConnection) page.openConnection();
	    conn.setRequestMethod("DELETE");
	    conn.setDoOutput(true);
	    conn=addRequestHeader(conn, requestParamMap);
	    if(xml!=null){
			OutputStream os = conn.getOutputStream();
		    BufferedWriter osw = new BufferedWriter(new OutputStreamWriter(os));
	        osw.write(xml.toString());
	        osw.flush();
	        osw.close();
		}
	    conn.connect();
	    StringBuffer text=processResponse(conn);
	     return text;
	  }
  
  
  private static StringBuffer processResponse(HttpURLConnection conn ){
	  
	  InputStreamReader in = null;
	  StringBuffer text=new StringBuffer();

	  try{
	    int responseCode=conn.getResponseCode();
	    System.out.println("response code:"+responseCode);
	    if (responseCode >=200 && responseCode<300) {
	    	/*recurly range of success 200-299*/
	    	in = new InputStreamReader(conn.getInputStream());  	
	    }
	    else if(responseCode >=400 && responseCode<500) {
	    	/*400–499 as client request errors*/
	    	in =  new InputStreamReader(conn.getErrorStream());	
	    }
	    else if(responseCode >=500 && responseCode<600) {
	    	/*500–599 as server errors*/
	    	in =  new InputStreamReader(conn.getErrorStream());	
	    }
	    
	    if(in!=null){
	    BufferedReader buff = new BufferedReader(in);
	    String line = buff.readLine();
	    while (line != null) {
	    	
	    	logger.debug(line);
	        text.append(line + "\n");
	       
	        line = buff.readLine();
	        
	    } 
	    buff.close();
	    in.close();
	    
	    }
	    
	    
	  }catch(Exception e){
		  e.printStackTrace();
		  if(in!=null){
			  try{in.close();}catch(Exception e1){in=null;}}
		  
	  }
	  System.out.println(text);
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

   
    
    