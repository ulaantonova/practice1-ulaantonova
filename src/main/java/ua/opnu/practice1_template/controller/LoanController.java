package ua.opnu.practice1_template.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.practice1_template.model.Loan;
import ua.opnu.practice1_template.repository.LoanRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loan")
public class LoanController {
    private final LoanRepository repo;

    public LoanController(LoanRepository repo) {
        this.repo = repo;
    }

    // Створити нову позику (взяти книгу)
    @PostMapping("/borrow")
    public ResponseEntity<Loan> borrow(@RequestBody Loan loan) {
        loan.setLoanDate(LocalDate.now());
        Loan savedLoan = repo.save(loan);
        return ResponseEntity.ok(savedLoan);
    }

    // Повернути книгу за ID позики
    @PostMapping("/return/{id}")
    public ResponseEntity<Loan> returnBook(@PathVariable Long id) {
        Optional<Loan> loan = repo.findById(id);
        if (loan.isPresent()) {
            loan.get().setReturnDate(LocalDate.now());
            Loan updatedLoan = repo.save(loan.get());
            return ResponseEntity.ok(updatedLoan);
        }
        return ResponseEntity.notFound().build();
    }

    // Отримати активні позики (не повернені)
    @GetMapping("/active")
    public ResponseEntity<List<Loan>> activeLoans() {
        List<Loan> activeLoans = repo.findByReturnDateIsNull();
        return ResponseEntity.ok(activeLoans);
    }

    // Отримати історію позик за bookId
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Loan>> bookHistory(@PathVariable Long bookId) {
        List<Loan> bookLoans = repo.findByBookId(bookId);
        return ResponseEntity.ok(bookLoans);
    }

    // Отримати історію позик за readerId
    @GetMapping("/reader/{readerId}")
    public ResponseEntity<List<Loan>> readerHistory(@PathVariable Long readerId) {
        List<Loan> readerLoans = repo.findByReaderId(readerId);
        return ResponseEntity.ok(readerLoans);
    }

    // Видалити позику за ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}