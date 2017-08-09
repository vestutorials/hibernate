package com.ves.tutorial.hibernate.search;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.lucene.search.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.ves.tutorial.hibernate.search.entity.Product;

/**
 * This example helps you to understand the Hibernate full-text search for
 * entities using EntityManagerFactory.
 * 
 * @author VES Tutorials
 *
 */
public class EntityManagerExample {
	public static void main(String[] args) throws InterruptedException {
		Configuration config = new Configuration();
		EntityManagerFactory entityManagerFactory = config.configure().buildSessionFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		createDummyEntries(entityManager);
		
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		
// 		TODO: Uncomment below statement when introducing Hibernate search in an
// 		existing application. This statement will create an initial Lucene index for
// 		the data already present in the database.

// 		fullTextSession.createIndexer(Product.class).startAndWait();
		fullTextEntityManager.getTransaction().begin();

		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Product.class)
				.get();
		
		Query luceneQuery = queryBuilder.keyword()
				.onFields("name", "model", "brand", "category", "vendor.name",
						"vendor.city", "vendor.state", "vendor.country")
				.matching("samsung")
				.createQuery();
		
		javax.persistence.Query query  = fullTextEntityManager
				.createFullTextQuery(luceneQuery, Product.class);
		
		List<Product> resultSet = query.getResultList();
		if(resultSet != null && !resultSet.isEmpty()) {
			resultSet.forEach(System.out::print); // Prints all Products using lambda expression and method reference
		}
		
		fullTextEntityManager.getTransaction().commit();
		
		fullTextEntityManager.close();
		entityManager.close();
		entityManagerFactory.close();
	}
	
	/**
	 * This method creates a dummy entries on product and vendor tables.
	 * 
	 * @param session
	 */
	public static void createDummyEntries(EntityManager entityManager) {
		entityManager.getTransaction().begin();

		List<Product> productList = DataUtil.createDummyData();
		productList.forEach(entityManager::persist); // Saves all Products using Lambda and method reference

		entityManager.getTransaction().commit();
	}
}
