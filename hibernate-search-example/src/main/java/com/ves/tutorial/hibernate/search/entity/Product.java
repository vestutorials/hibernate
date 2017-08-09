package com.ves.tutorial.hibernate.search.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name = "product")
@Indexed
public class Product {
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Column
	private String name;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Column
	private String model;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Column
	private String brand;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Column
	private String category;

	public Product() {
	}

	public Product(String name, String model, String brand, String category, Vendor vendor) {
		super();
		this.name = name;
		this.model = model;
		this.brand = brand;
		this.category = category;
		this.vendor = vendor;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@IndexedEmbedded
	private Vendor vendor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", model=" + model + ", brand=" + brand + ", category="
				+ category + ", vendor=" + vendor + "]\n";
	}

}
