package ua.opnu.practice1_template;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByBookId(Long bookId);
    List<Loan> findByReaderId(Long readerId);
    List<Loan> findByReturnDateIsNull();
}
