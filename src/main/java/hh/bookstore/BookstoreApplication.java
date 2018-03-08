package hh.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.bookstore.domain.Book;
import hh.bookstore.domain.BookRepository;
import hh.bookstore.domain.Category;
import hh.bookstore.domain.CategoryRepository;
import hh.bookstore.domain.User;
import hh.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");		
			crepository.save(new Category("Sarjakuva"));
			crepository.save(new Category("Tietokirja"));
			crepository.save(new Category("Kaunokirjallisuus"));
		
			brepository.save(new Book("Testi 1", "Testaaja 1", "978-952-312-672-5", 2000, 18.95, crepository.findByName("Sarjakuva").get(0)));
			brepository.save(new Book("Testi 2", "Testaaja 2", "978-952-312-672-5", 2001, 11.22, crepository.findByName("Sarjakuva").get(0)));
			brepository.save(new Book("Testi 3", "Testaaja 3", "978-952-312-672-5", 2002, 11.22, crepository.findByName("Sarjakuva").get(0)));
			
			urepository.save(new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "user@user.fi"));
			urepository.save(new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "admin@admin.fi"));
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
			log.info("fetch all categories");
			for (Category category : crepository.findAll()) {
				log.info(category.toString());
			}
			
		};
	}
}