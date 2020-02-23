package fi.demot.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.demot.bookstore.domain.Book;
import fi.demot.bookstore.domain.BookRepository;
import fi.demot.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository catrepository;

	@RequestMapping(value = { "/", "/booklist" })
	public String bookList(Model model) {

		model.addAttribute("books", repository.findAll());

		return "booklist";

	}

	@RequestMapping(value = "/add")
	public String addBook(Model model) {

		model.addAttribute("book", new Book());
		model.addAttribute("categories", catrepository.findAll());

		return "addbook";

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {

		repository.save(book);

		return "redirect:booklist";

	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {

		repository.deleteById(id);

		return "redirect:../booklist";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) {

		model.addAttribute("editBook", repository.findById(bookId).get());
		model.addAttribute("categories", catrepository.findAll());
		
		return "editbook";
	}

	
}
