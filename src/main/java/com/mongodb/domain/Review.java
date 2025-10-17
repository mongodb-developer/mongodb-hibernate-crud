package com.mongodb.domain;

import jakarta.persistence.Embeddable;
import org.hibernate.annotations.Struct;

@Embeddable
@Struct(name = "Review")
public class Review {

   private String author;
   private String title;
   private String comment;
   private double rating;
   public Review() {}

   public Review(String author, String title, String comment, double rating) {
      this.author = author;
      this.title = title;
      this.comment = comment;
      this.rating = rating;
   }

   @Override
   public String toString() {
      return "Review{author='%s', title='%s', comment='%s', rating=%.1f}"
              .formatted(author, title, comment, rating);
   }
}
