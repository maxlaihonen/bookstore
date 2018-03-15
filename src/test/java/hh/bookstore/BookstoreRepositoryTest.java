package hh.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.bookstore.domain.Book;
import hh.bookstore.domain.BookRepository;
import hh.bookstore.domain.Category;
import hh.bookstore.domain.CategoryRepository;
import hh.bookstore.domain.User;
import hh.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTest {

	@Autowired
	private BookRepository brepository;

	@Autowired
	private CategoryRepository crepository;

	@Autowired
	private UserRepository urepository;

	@Test
	public void findBookByTitleTest() {
		List<Book> books = brepository.findByTitle("Testi 1");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getIsbn()).isEqualTo("978-952-312-672-5");
	}

	@Test
	public void findCategoryByNameTest() {
		List <Category> categories = crepository.findByName("Sarjakuva");
		assertThat(categories).hasSize(1);
	}

	@Test
	public void findUserByNameTest() {
		User user = urepository.findByUsername("user");
		assertThat(user.getEmail()).isEqualTo("user@user.fi");

	}

	@Test
	public void createBookTest() {
		Book book = new Book("Testauskirja", "Juhani Merimaa", "23456789-8", 1993, 9.6, crepository.findByName("Sarjakuva").get(0));
		brepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteBookTest() {
		brepository.delete(2L);
		assertThat(brepository.findByTitle("Testi 2")).hasSize(0);
	}



}