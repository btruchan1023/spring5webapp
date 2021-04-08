package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author ben = new Author("Ben", "Truchan");
        Book book = new Book("The Life of Leelah", "123123");
        Publisher leelah = new Publisher("Leelah", "123 Kibblet way", "La Crosse", "Wisconsin", "54601");
        ben.getBooks().add(book);
        book.getAuthors().add(ben);

        publisherRepository.save(leelah);

        book.setPublisher(leelah);
        leelah.getBooks().add(book);

        authorRepository.save(ben);
        bookRepository.save(book);
        publisherRepository.save(leelah);

        Author britt = new Author("Britt", "Moreno");
        Book brittbook = new Book("I want a dog", "321321");
        britt.getBooks().add(brittbook);
        brittbook.getAuthors().add(britt);
        brittbook.setPublisher(leelah);
        leelah.getBooks().add(brittbook);

        authorRepository.save(britt);
        bookRepository.save(brittbook);
        publisherRepository.save(leelah);

        System.out.println("This started from Bootstrap!");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println(leelah);
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Publisher Number of Books: " + leelah.getBooks().size());
    }
}
