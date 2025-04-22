package ua.opnu.practice1_template;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorId(Long authorId);

    List<Book> findByGenreId(Long genreId);
}
