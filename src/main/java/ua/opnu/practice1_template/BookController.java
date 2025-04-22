package ua.opnu.practice1_template;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
class BookController {
    private final BookRepository repo;

    BookController(BookRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Book add(@RequestBody Book b) {
        return repo.save(b);
    }

    @GetMapping
    public List<Book> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book b) {
        b.id = id;
        return repo.save(b);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @GetMapping("/author/{authorId}")
    public List<Book> byAuthor(@PathVariable Long authorId) {
        return repo.findByAuthorId(authorId);
    }

    @GetMapping("/genre/{genreId}")
    public List<Book> byGenre(@PathVariable Long genreId) {
        return repo.findByGenreId(genreId);
    }
}
