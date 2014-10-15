package entity;
// default package
// Generated Feb 26, 2014 5:12:18 PM by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Categories generated by hbm2java
 */
@Entity
@Table(name = "Categories", schema = "dbo", catalog = "warehouseDB")
public class Categories implements Serializable {

	/** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;
    private int categoryId;
	private String name;
	private Set<Products> productses = new HashSet<Products>(0);

	public Categories() {
	}

	public Categories(int categoryId, String name) {
		this.categoryId = categoryId;
		this.name = name;
	}

	public Categories(int categoryId, String name,
			Set<Products> productses) {
		this.categoryId = categoryId;
		this.name = name;
		this.productses = productses;
	}

	@Id
	@Column(name = "CategoryId", unique = true, nullable = false)
	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "Name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categories")
	public Set<Products> getProductses() {
		return this.productses;
	}

	public void setProductses(Set<Products> productses) {
		this.productses = productses;
	}

}