package ua.opnu.practice1_template;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")
class ReaderController {
    private final ReaderRepository repo;

    ReaderController(ReaderRepository repo) { this.repo = repo; }

    @PostMapping
    public Reader register(@RequestBody Reader r) { return repo.save(r); }
    @GetMapping
    public List<Reader> all() { return repo.findAll(); }
    @GetMapping("/{id}") public Reader get(@PathVariable Long id) { return repo.findById(id).orElse(null); }
    @PutMapping("/{id}") public Reader update(@PathVariable Long id, @RequestBody Reader r) {
        r.id = id;
        return repo.save(r);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
