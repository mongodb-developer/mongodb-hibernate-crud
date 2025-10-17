package com.mongodb;

import com.mongodb.config.HibernateUtil;
import com.mongodb.domain.Book;
import com.mongodb.domain.Review;
import com.mongodb.service.BookService;
import com.mongodb.service.ReviewService;
import org.bson.types.ObjectId;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class MyApplication {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Scanner sc = new Scanner(System.in);
		BookService bookService = new BookService();
		ReviewService reviewService = new ReviewService();

		int option;

		do {
			System.out.println("\n=== BOOK MENU ===");
			System.out.println("1 - Add Book");
			System.out.println("2 - List Books");
			System.out.println("3 - Update Book");
			System.out.println("4 - Delete Book");
			System.out.println("5 - Find Books by Minimum Pages");
			System.out.println("6 - Add Review");
			System.out.println("7 - List Books and Reviews by Id");
			System.out.println("0 - Exit");
			System.out.print("Choose: ");
			option = sc.nextInt();
			sc.nextLine();

			switch (option) {
				case 1 -> {
					System.out.print("Title: ");
					String title = sc.nextLine();

					System.out.print("Number of pages: ");
					int pages = sc.nextInt();
					sc.nextLine();

					var book = bookService.create(title, pages);
					System.out.println("Created: " + book);
				}

				case 2 -> {
					List<Book> books = bookService.findAll();
					books.forEach(System.out::println);
				}

				case 3 -> {
					System.out.print("Book ID: ");
					String id = sc.nextLine();

					System.out.print("New Title: ");
					String newTitle = sc.nextLine();

					System.out.print("New Page Count: ");
					int newPages = sc.nextInt();
					sc.nextLine();

					Book updated = new Book(new ObjectId(id), newTitle, newPages);
					boolean ok = bookService.update(updated);
					System.out.println(ok ? "Book updated successfully!" : "Book not found.");
				}

				case 4 -> {
					System.out.print("Book ID to delete: ");
					String id = sc.nextLine();
					boolean deleted = bookService.deleteById(new ObjectId(id));
					System.out.println(deleted ? "Book deleted successfully!" : "Book not found.");
				}
				case 5 -> {
					System.out.print("Enter the minimum number of pages: ");
					String pages = sc.nextLine();
					List<Book> books = bookService.findBooksWithPagesGreaterThanOrEqual(Integer.parseInt(pages));

					books.forEach(System.out::println);
				}
				case 6 -> {
					System.out.print("Book ID: ");
					String bookId = sc.nextLine();
					System.out.print("Author: ");
					String author = sc.nextLine();
					System.out.print("Review Title: ");
					String rTitle = sc.nextLine();
					System.out.print("Comment: ");
					String comment = sc.nextLine();
					System.out.print("Rating: ");
					double rating = sc.nextDouble();
					sc.nextLine();

					reviewService.insert(new Review(author, new ObjectId(bookId), rTitle, comment, rating));
					System.out.println("Review added!");
				}

				case 7 -> {
					System.out.print("Book ID: ");
					String bookId = sc.nextLine();
					BookService.BookWithReviews br = bookService.findAllBooksWithReviewsById(new ObjectId(bookId));

					System.out.printf("\n%s - %s (%d reviews)\n",
							br.book().getId(), br.book().getTitle(), br.reviews().size());
					br.reviews().forEach(System.out::println);
				}

				case 0 -> System.out.println("Bye!");
				default -> System.out.println("Invalid option, try again.");
			}

		} while (option != 0);

		sc.close();
		factory.close();
	}
}
