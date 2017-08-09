package com.ves.tutorial.hibernate.search.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name = "vendor")
public class Vendor {
	@Id
	@GeneratedValue
	private Integer id;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String name;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String city;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String state;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String country;
	@OneToMany(mappedBy = "vendor")
	private List<Product> products;

	public Vendor() {
	}

	public Vendor(String name, String city, String state, String country) {
		this.name = name;
		this.city = city;
		this.state = state;
		this.country = country;
	}

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Vendor [id=" + id + ", name=" + name + ", city=" + city + ", state=" + state + ", country=" + country
				+ "]";
	}

}
