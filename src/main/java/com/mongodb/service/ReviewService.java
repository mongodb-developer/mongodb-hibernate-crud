package com.mongodb.service;

import com.mongodb.config.HibernateUtil;
import com.mongodb.domain.Review;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReviewService {

   public void insert(Review review) {
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         Transaction tx = session.beginTransaction();
         session.persist(review);
         tx.commit();
         System.out.println("Review inserted: " + review);
      }
   }
}
