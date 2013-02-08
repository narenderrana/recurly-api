package com.recurly.v2;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "invoice")
public class InvoiceDetailed   {

	@XmlElement(name = "subtotal_in_cents")
	public Integer subtotal_in_cents;

	@XmlElement(name = "total_in_cents")
	public Integer total_in_cents;

	@XmlElement(name = "vat_amount_in_cents")
	public Integer vat_amount_in_cents;

	@XmlElement(name = "paid_in_cents")
	public Integer paid_in_cents;

	@XmlElement(name = "total_due_in_cents")
	public Integer total_due_in_cents;

	@XmlElement(name = "discount_in_cents")
	public Integer discount_in_cents;

	@XmlElement(name = "id")
	public String id;

	@XmlElement(name = "account_code")
	public String account_code;

	@XmlElement(name = "invoice_number")
	public Integer invoice_number;

	@XmlElement(name = "vat_number")
	public String vat_number;

	@XmlElement(name = "status")
	public String status;

	@XmlElementWrapper(name = "payments")
	@XmlElement(name = "payment")
	public List<Payment> payment;

	@XmlElementWrapper(name = "line_items")
	@XmlElement(name = "line_item")
	public List<LineItem> line_item;

 
}
