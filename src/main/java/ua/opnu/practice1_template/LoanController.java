package ua.opnu.practice1_template;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loan")
class LoanController {
    private final LoanRepository repo;

    LoanController(LoanRepository repo) { this.repo = repo; }
    @GetMapping
    public List<Loan> all() {
        return repo.findAll();
    }
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
