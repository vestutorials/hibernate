package com.ves.tutorial.hibernate.search;

import java.util.ArrayList;
import java.util.List;

import com.ves.tutorial.hibernate.search.entity.Product;
import com.ves.tutorial.hibernate.search.entity.Vendor;

public class DataUtil {
	public static List<Product> createDummyData() {
		List<Product> productList = new ArrayList<>();

		Vendor vendor1 = new Vendor("Ultra Products", "Portland", "Oregon", "United States");
		Vendor vendor2 = new Vendor("CG Products", "Los Angeles", "California", "United States");

		productList.add(new Product("Samsung Galaxy On5", "SM-G550FZKDINS", "Samsung", "Smartphone", vendor1));
		productList.add(new Product("Samsung Galaxy J3 Pro", "SM-J320FZDGINS", "Samsung", "Smartphone", vendor1));
		productList.add(new Product("Lenovo Vibe K5 Plus", "A6020a46", "Lenovo", "Smartphone", vendor1));
		productList.add(new Product("Apple iPhone 6s", "MKQK2HN", "Apple", "Smartphone", vendor1));
		productList.add(new Product("Apple iPhone 5s", "A1530", "Apple", "Smartphone", vendor1));
		productList.add(new Product("Moto M", "XT1663", "Motorola", "Electronics", vendor1));
		productList
				.add(new Product("Sony 80cm (32) HD Ready LED Smart TV", "KLV-32W622E", "Sony", "Smart TV", vendor1));
		productList.add(new Product("Samsung 123cm (49) Ultra HD (4K) Curved LED Smart TV ", "49KS7500", "Samsung",
				"Smart TV", vendor2));
		productList.add(new Product("LG 108cm (43) Full HD LED Smart TV", "43LH576T", "LG", "Smart TV", vendor2));
		productList.add(new Product("Microsoft Surface Core m3 6th Gen", "Pro 4", "Microsoft", "Laptop", vendor2));

		return productList;
	}
}
