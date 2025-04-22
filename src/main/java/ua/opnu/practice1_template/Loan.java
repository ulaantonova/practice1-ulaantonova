package ua.opnu.practice1_template;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDate;

@Entity
@Getter
@Setter
class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @ManyToOne
    @JsonManagedReference
    Book book;

    @ManyToOne
    @JsonManagedReference
    Reader reader;

    LocalDate loanDate;
    LocalDate returnDate;
}
