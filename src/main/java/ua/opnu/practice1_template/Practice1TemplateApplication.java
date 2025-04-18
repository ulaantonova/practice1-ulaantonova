package ua.opnu.practice1_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



import lombok.Getter;
import lombok.Setter;






@SpringBootApplication
public class Practice1TemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(Practice1TemplateApplication.class, args);
    }

}



@Entity
@Getter
@Setter
class Author {
    @Id @GeneratedValue
    Long id;
    String name;
    LocalDate birthDate;


}

@Entity
@Getter
@Setter
class Book {
    @Id @GeneratedValue
    Long id;
    String title;
   String autor;
   Integer yearPublished;
   String isbn;
    @ManyToOne
    private Author author;
    @ManyToOne
    private Genre genre;
}

@Entity
@Getter
@Setter
class Reader {
    @Id @GeneratedValue
    Long id;
    String firstName;
    String lastName;
    String libraryCardNumber;
    LocalDate registeredAt;
}
@Entity
@Getter
@Setter
class Loan {
    @Id @GeneratedValue
    Long id;


    @ManyToOne
    Book book;

    @ManyToOne
    Reader reader;

    LocalDate loanDate;
    LocalDate returnDate;
}


@Entity
@Getter
@Setter
class Genre {
    @Id @GeneratedValue
    Long id;
    String name;
}


interface AuthorRepository extends JpaRepository<Author, Long> {}
interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorId(Long authorId);
    List<Book> findByGenreId(Long genreId);
}
interface ReaderRepository extends JpaRepository<Reader, Long> {}
interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByBookId(Long bookId);
    List<Loan> findByReaderId(Long readerId);
    List<Loan> findByReturnDateIsNull();
}
interface GenreRepository extends JpaRepository<Genre, Long> {}







@RestController
@RequestMapping("/books")
class BookController {
    private final BookRepository repo;

    BookController(BookRepository repo) { this.repo = repo; }

    @PostMapping public Book add(@RequestBody Book b) { return repo.save(b); }
    @GetMapping public List<Book> all() { return repo.findAll(); }
    @GetMapping("/{id}") public Book get(@PathVariable Long id) { return repo.findById(id).orElse(null); }
    @PutMapping("/{id}") public Book update(@PathVariable Long id, @RequestBody Book b) {
        b.id = id;
        return repo.save(b);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { repo.deleteById(id); }
    @GetMapping("/author/{authorId}") public List<Book> byAuthor(@PathVariable Long authorId) { return repo.findByAuthorId(authorId); }
    @GetMapping("/genre/{genreId}") public List<Book> byGenre(@PathVariable Long genreId) { return repo.findByGenreId(genreId); }
}

@RestController
@RequestMapping("/authors")
class AuthorController {
    private final AuthorRepository repo;

    AuthorController(AuthorRepository repo) { this.repo = repo; }

    @PostMapping public Author add(@RequestBody Author a) { return repo.save(a); }
    @GetMapping public List<Author> all() { return repo.findAll(); }
    @PutMapping("/{id}") public Author update(@PathVariable Long id, @RequestBody Author a) {
        a.id = id;
        return repo.save(a);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { repo.deleteById(id); }
}

@RestController
@RequestMapping("/readers")
class ReaderController {
    private final ReaderRepository repo;

    ReaderController(ReaderRepository repo) { this.repo = repo; }

    @PostMapping public Reader register(@RequestBody Reader r) { return repo.save(r); }
    @GetMapping public List<Reader> all() { return repo.findAll(); }
    @GetMapping("/{id}") public Reader get(@PathVariable Long id) { return repo.findById(id).orElse(null); }
    @PutMapping("/{id}") public Reader update(@PathVariable Long id, @RequestBody Reader r) {
        r.id = id;
        return repo.save(r);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { repo.deleteById(id); }
}

@RestController
@RequestMapping("/loans")
class LoanController {
    private final LoanRepository repo;

    LoanController(LoanRepository repo) { this.repo = repo; }

    @PostMapping("/borrow")
    public Loan borrow(@RequestBody Loan l) {
        l.loanDate = LocalDate.now();
        return repo.save(l);
    }

    @PostMapping("/return/{id}")
    public Loan returnBook(@PathVariable Long id) {
        Optional<Loan> loan = repo.findById(id);
        if (loan.isPresent()) {
            loan.get().returnDate = LocalDate.now();
            return repo.save(loan.get());
        }
        return null;
    }

    @GetMapping("/active") public List<Loan> activeLoans() { return repo.findByReturnDateIsNull(); }
    @GetMapping("/book/{bookId}") public List<Loan> bookHistory(@PathVariable Long bookId) { return repo.findByBookId(bookId); }
    @GetMapping("/reader/{readerId}") public List<Loan> readerHistory(@PathVariable Long readerId) { return repo.findByReaderId(readerId); }
}

@RestController
@RequestMapping("/genres")
class GenreController {
    private final GenreRepository repo;

    GenreController(GenreRepository repo) { this.repo = repo; }

    @PostMapping public Genre add(@RequestBody Genre g) { return repo.save(g); }
    @GetMapping public List<Genre> all() { return repo.findAll(); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
