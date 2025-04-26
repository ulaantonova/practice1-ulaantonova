package ua.opnu.practice1_template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.opnu.practice1_template.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
