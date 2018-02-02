package hh.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.bookstore.domain.BookRepository;


@Controller

public class BookstoreController {
	@Autowired
	private BookRepository repository; 
	
    @RequestMapping(value="/index")
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "index";
    }
}
