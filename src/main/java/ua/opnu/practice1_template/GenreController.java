package ua.opnu.practice1_template;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
class GenreController {
    private final GenreRepository repo;

    GenreController(GenreRepository repo) { this.repo = repo; }

    @PostMapping
    public Genre add(@RequestBody Genre g) { return repo.save(g); }
    @GetMapping
    public List<Genre> all() { return repo.findAll(); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
