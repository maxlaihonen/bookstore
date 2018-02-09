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

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookrepository, CategoryRepository categoryrepository) {
		return (args) -> {
			log.info("save a couple of books");		
			categoryrepository.save(new Category("Sarjakuva"));
			categoryrepository.save(new Category("Tietokirja"));
			categoryrepository.save(new Category("Kaunokirjallisuus"));
		
			bookrepository.save(new Book("Testi 1", "Testaaja 1", "978-952-312-672-5", 2000, 18.95, categoryrepository.findByName("Sarjakuva").get(0)));
			bookrepository.save(new Book("Testi 2", "Testaaja 2", "978-952-312-672-5", 2001, 11.22, categoryrepository.findByName("Sarjakuva").get(0)));
			bookrepository.save(new Book("Testi 3", "Testaaja 3", "978-952-312-672-5", 2002, 11.22, categoryrepository.findByName("Sarjakuva").get(0)));
		
			log.info("fetch all books");
			for (Book book : bookrepository.findAll()) {
				log.info(book.toString());
			}
			log.info("fetch all categories");
			for (Category category : categoryrepository.findAll()) {
				log.info(category.toString());
			}

		};
	}
}
