package fi.demot.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.demot.bookstore.domain.Book;
import fi.demot.bookstore.domain.BookRepository;
import fi.demot.bookstore.domain.Category;
import fi.demot.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);

	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository catrepository) {

		// syötetään H2:een materiaalia
		return (args) -> {


			log.info("save categories to H2-database");
			catrepository.save(new Category("detective"));
			catrepository.save(new Category("document"));
			catrepository.save(new Category("classic"));


			log.info("save a couple of books");
			repository.save(new Book("Seitsemän veljestä", "Aleksis Kiva", 1870, "234677", 34.5, catrepository.findByName("detective").get(0)));
			repository.save(new Book("Tuntematon sotilas", "Väinö Linna", 1952, "457689", 35.5, catrepository.findByName("detective").get(0)));

			
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());

			}

			log.info("fetch all categories");
			for (Category category : catrepository.findAll()) {
				log.info(category.getName());
			}

		};

	}

}
