package com.ves.tutorial.hibernate.search;

import java.util.List;

import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.ves.tutorial.hibernate.search.entity.Product;

/**
 * This example helps you to understand the Hibernate full-text search for
 * entities using SessionFactory.
 * 
 * @author VES Tutorials
 *
 */
public class SessionFactoryExample {
	public static void main(String[] args) throws InterruptedException {
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		
		createDummyEntries(session);
		
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		
// 		TODO: Uncomment below statement when introducing Hibernate search in an
// 		existing application. This statement will create an initial Lucene index for
// 		the data already present in the database.

// 		fullTextSession.createIndexer(Product.class).startAndWait();
		fullTextSession.getTransaction().begin();

		QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Product.class)
				.get();
		
		Query luceneQuery = queryBuilder.keyword()
				.onFields("name", "model", "brand", "category", "vendor.name",
						"vendor.city", "vendor.state", "vendor.country")
				.matching("samsung")
				.createQuery();
		
		javax.persistence.Query query  = fullTextSession
				.createFullTextQuery(luceneQuery, Product.class);
		
		List<Product> resultSet = query.getResultList();
		if(resultSet != null && !resultSet.isEmpty()) {
			resultSet.forEach(System.out::print); // Prints all Products using lambda expression and method reference
		}
		
		fullTextSession.getTransaction().commit();
		
		fullTextSession.close();
		session.close();
		sessionFactory.close();
	}
	
	/**
	 * This method creates a dummy entries on product and vendor tables.
	 * 
	 * @param session
	 */
	public static void createDummyEntries(Session session) {
		session.getTransaction().begin();

		List<Product> productList = DataUtil.createDummyData(); 
		productList.forEach(session::persist); // Saves all Products using Lambda and method reference
		session.getTransaction().commit();
	}
}
