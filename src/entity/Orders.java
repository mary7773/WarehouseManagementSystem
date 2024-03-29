package entity;


// Generated Mar 1, 2014 7:49:42 PM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Orders generated by hbm2java
 */
public class Orders
    implements java.io.Serializable
{

    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;
    private int orderId;
    private Products products;
    private Users users;
    private Payment payment;
    private Date orderDate;
    private Integer invoiceId;
    private BigDecimal orderSum;
    private Set<Invoice> invoices = new HashSet<Invoice>(0);


    public Orders()
    {}


    public Orders(int orderId, Users users, Date orderDate, BigDecimal orderSum)
    {
        this.orderId = orderId;
        this.users = users;
        this.orderDate = orderDate;
        this.orderSum = orderSum;
    }


    public Orders(int orderId,
                  Products products,
                  Users users,
                  Payment payment,
                  Date orderDate,
                  Integer invoiceId,
                  BigDecimal orderSum,
                  Set<Invoice> invoices)
    {
        this.orderId = orderId;
        this.products = products;
        this.users = users;
        this.payment = payment;
        this.orderDate = orderDate;
        this.invoiceId = invoiceId;
        this.orderSum = orderSum;
        this.invoices = invoices;
    }


    public int getOrderId()
    {
        return this.orderId;
    }


    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }


    public Products getProducts()
    {
        return this.products;
    }


    public void setProducts(Products products)
    {
        this.products = products;
    }


    public Users getUsers()
    {
        return this.users;
    }


    public void setUsers(Users users)
    {
        this.users = users;
    }


    public Payment getPayment()
    {
        return this.payment;
    }


    public void setPayment(Payment payment)
    {
        this.payment = payment;
    }


    public Date getOrderDate()
    {
        return this.orderDate;
    }


    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }


    public Integer getInvoiceId()
    {
        return this.invoiceId;
    }


    public void setInvoiceId(Integer invoiceId)
    {
        this.invoiceId = invoiceId;
    }


    public BigDecimal getOrderSum()
    {
        return this.orderSum;
    }


    public void setOrderSum(BigDecimal orderSum)
    {
        this.orderSum = orderSum;
    }


    public Set<Invoice> getInvoices()
    {
        return this.invoices;
    }


    public void setInvoices(Set<Invoice> invoices)
    {
        this.invoices = invoices;
    }

}
