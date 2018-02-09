package hh.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.bookstore.domain.Book;
import hh.bookstore.domain.BookRepository;
import hh.bookstore.domain.CategoryRepository;


@Controller
public class BookstoreController {
	@Autowired
	private BookRepository bookrepository; 
	
	@Autowired
	private CategoryRepository categoryrepository; 
	
    @RequestMapping(value="/index")
    public String bookList(Model model) {	
        model.addAttribute("books", bookrepository.findAll());
        return "index";
    }
    
    
    /*
     * REST aiheuttaa virheen ohjelman käynnistyessä. Poistettu toistaiseksi.
     * 
    @RequestMapping(value="/api", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) bookrepository.findAll();
	}

    @RequestMapping(value="/api/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findbookRest(@PathVariable("id") Long bookId) {
    	return (Book) bookrepository.findOne(bookId);
    }
    */
    
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", categoryrepository.findAll());
        return "addbook";
    }   
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        bookrepository.save(book);
        return "redirect:index";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    bookrepository.delete(bookId);
        return "redirect:/index";
    }  
    
}
