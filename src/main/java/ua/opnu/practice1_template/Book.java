package ua.opnu.practice1_template;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    Integer yearPublished;
    String isbn;
    @ManyToOne
    private Author author;
    @ManyToOne
    private Genre genre;
}
