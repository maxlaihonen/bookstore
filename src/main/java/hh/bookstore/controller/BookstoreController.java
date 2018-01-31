package hh.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class BookstoreController {
	@RequestMapping(value="/index")
	public String index() {
		return "index";
	}
}
