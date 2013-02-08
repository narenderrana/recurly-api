package com.recurly.v2;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "transaction")
public class Transaction  {

 
/*
	@XmlElement(name = "id")
	public String id;
	
	@XmlElement(name = "invoice_id")
	public String invoiceId;
	@XmlElement(name = "message")
	public String message;
	
	@XmlElement(name = "billing_info")
	public BillingInfo billingInfo;

	@XmlElement(name = "credit_card")
	public CreditCard creditCard;

	@XmlElement(name = "invoice")
	public Invoice invoice;

	@XmlElement(name = "plan")
	public Plan plan;
	@XmlElement(name = "account")
	public Account account;

	@XmlElement(name = "subscription")
	public Subscription subscription;

	@XmlElement(name = "amount_in_cents")
	public Integer amountInCents;
	@XmlElement(name = "transaction")
	public Transaction transaction;	
	@XmlElement(name = "credit_card")
	public CreditCard credit_card;
		
*/
	
	/*@XmlElement(name="account") 					
	public Account account;
	@XmlElement(name="invoice") 					
	public String invoice;
	@XmlElement(name="subscription") 					
	public String subscription;*/
	
	
	
	@XmlElement(name="uuid")
	public String uuid;
	@XmlElement(name = "action")
	public String action;
	@XmlElement(name = "currency")
	public String currency;
	@XmlElement(name="amount_in_cents")
	public Integer amountCents;
	@XmlElement(name = "tax_in_cents")
	public Integer taxinCents;
	@XmlElement(name = "status")
	public String status;
	@XmlElement(name = "reference")
	public String reference;
	@XmlElement(name = "source")
	public String source;
	@XmlElement(name = "test")
	public Boolean test;
	@XmlElement(name = "voidable")
	public Boolean voidable;
	@XmlElement(name = "refundable")
	public Boolean refundable;
	@XmlElement(name = "cvv_result")
	public String cvvResult;
	@XmlElement(name = "avs_result")
	public String avsResult;
	@XmlElement(name = "avs_result_street")
	public String avsResultStreet;
	@XmlElement(name = "avs_result_postal")
	public String avsResultPostal;
	@XmlElement(name = "created_at")
	public Date date;
	
	/*@XmlElement(name="details")
	public  TransactionBasic details;*/
	 @XmlElement(name="details")
	public  List<Account> details; 
	
	
	
	
 
}