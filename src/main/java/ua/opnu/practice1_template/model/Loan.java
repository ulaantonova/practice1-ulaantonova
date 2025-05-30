package ua.opnu.practice1_template.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JsonManagedReference
   private Book book;

    @ManyToOne
    @JsonManagedReference
   private Reader reader;

     private LocalDate loanDate;
   private LocalDate returnDate;
}
