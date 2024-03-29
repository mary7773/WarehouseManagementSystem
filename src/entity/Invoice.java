package entity;

// Generated Mar 1, 2014 7:49:42 PM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Invoice generated by hbm2java
 */
public class Invoice implements java.io.Serializable {

	/** field <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	private int invoiceId;
	private Date dateInvoice;
	private int invoiceNumber;
	private Users customer;
	private Users company;
	private Products product;
	private double quanity;
	private List<Products> products;
	private Set<Orders> orderses;
	private int percent;
	private double total;

	public Invoice() {
		products = new ArrayList<Products>();
		orderses = new HashSet<Orders>(0);
	}

//	public Invoice(int invoiceId, Date dateInvoice, int invoiceNumber) {
//		this.invoiceId = invoiceId;
//		this.dateInvoice = dateInvoice;
//		this.invoiceNumber = invoiceNumber;
//	}
//
//	public Invoice(Date date, int invoiceNumber, Products pr, Users custemer,
//			Users company, double quantity) {
//
//		this.dateInvoice = date;
//		this.invoiceNumber = invoiceNumber;
//		this.product = pr;
//		this.company = company;
//		this.customer = custemer;
//		this.quanity = quantity;
//
//	}

	public int getPercent() {
		return this.percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

//	public Invoice(int invoiceId, Date dateInvoice, int quantity,
//			int invoiceNumber, List<Products> pr) {
//		this.invoiceId = invoiceId;
//		this.dateInvoice = dateInvoice;
//		this.invoiceNumber = invoiceNumber;
//		this.products = pr;
//	}

	public int getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}


	public Date getDateInvoice() {
		return this.dateInvoice;
	}

	public void setDateInvoice(Date dateInvoice) {
		this.dateInvoice = dateInvoice;
	}

	public int getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public Users getCustomer() {
		return customer;
	}

	public void setCustomer(Users customer) {
		this.customer = customer;
	}

	public Users getCompany() {
		return company;
	}

	public void setCompany(Users company) {
		this.company = company;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public double getQuantity() {
		return quanity;
	}

	public void setQuantity(double quanity) {
		this.quanity = quanity;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public List<Products> getProducts() {
		return this.products;
	}

	public void addProduct(Products pr) {
		
		this.products.add(pr);
	}

	
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}
	
	public double getRowTotal(){
		double total = 0;
		for (Products pr : getProducts()) {
			total+=pr.getTotal();
		}
		return total;
	}
	

}
