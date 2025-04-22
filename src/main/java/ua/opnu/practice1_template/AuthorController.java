package ua.opnu.practice1_template;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
class AuthorController {
    private final AuthorRepository repo;

    AuthorController(AuthorRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Author> all() {
        return repo.findAll();
    }

    @PostMapping
    public Author add(@RequestBody Author a) {
        return repo.save(a);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable Long id, @RequestBody Author a) {
        a.setId(id); // Використовуємо setId() замість прямого доступу до author.id
        return repo.save(a);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
