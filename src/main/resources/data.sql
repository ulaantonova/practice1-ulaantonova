-- Скидаємо лічильники ID (щоб почати з 1)
ALTER SEQUENCE author_seq RESTART WITH 1;
ALTER SEQUENCE genre_seq RESTART WITH 1;
ALTER SEQUENCE book_seq RESTART WITH 1;
ALTER SEQUENCE reader_seq RESTART WITH 1;
ALTER SEQUENCE loan_seq RESTART WITH 1;

-- Спочатку видаляємо всі записи в таблицях, щоб уникнути порушення зовнішніх ключів
DELETE FROM loan;
DELETE FROM book;
DELETE FROM reader;
DELETE FROM genre;
DELETE FROM author;

-- Додаємо авторів з явно заданим ID
INSERT INTO author (id, name, birth_date) VALUES (1, 'John Doe', '1970-01-01');
INSERT INTO author (id, name, birth_date) VALUES (2, 'Jane Smith', '1985-05-15');
INSERT INTO author (id, name, birth_date) VALUES (3, 'Mark Twain', '1835-11-30');
INSERT INTO author (id, name, birth_date) VALUES (4, 'George Orwell', '1903-06-25');
INSERT INTO author (id, name, birth_date) VALUES (5, 'J.K. Rowling', '1965-07-31');
INSERT INTO author (id, name, birth_date) VALUES (6, 'H.G. Wells', '1866-09-21');
INSERT INTO author (id, name, birth_date) VALUES (7, 'Isaac Asimov', '1920-01-02');
INSERT INTO author (id, name, birth_date) VALUES (8, 'Agatha Christie', '1890-09-15');





-- Додаємо жанри з явно заданим ID
INSERT INTO genre (id, name) VALUES (1, 'Fiction');
INSERT INTO genre (id, name) VALUES (2, 'Non-Fiction');
INSERT INTO genre (id, name) VALUES (3, 'Adventure');
INSERT INTO genre (id, name) VALUES (4, 'Fantasy');
INSERT INTO genre (id, name) VALUES (5, 'Mystery');
INSERT INTO genre (id, name) VALUES (6, 'Science Fiction');
INSERT INTO genre (id, name) VALUES (7, 'Historical Fiction');
INSERT INTO genre (id, name) VALUES (8, 'Thriller');

-- Додаємо книги з явно заданим ID авторів і жанрів
INSERT INTO book (id, title, year_published, isbn, author_id, genre_id)
VALUES (1, 'About tea', 2020, '1234567890', 1, 1);

INSERT INTO book (id, title, year_published, isbn, author_id, genre_id)
VALUES (2, 'History', 2021, '0987654321', 2, 2);

INSERT INTO book (id, title, year_published, isbn, author_id, genre_id)
VALUES (3, 'Adventures of Tom Sawyer', 1876, '1112223334', 3, 3);

INSERT INTO book (id, title, year_published, isbn, author_id, genre_id)
VALUES (4, '1984', 1949, '1231231234', 4, 1);

INSERT INTO book (id, title, year_published, isbn, author_id, genre_id)
VALUES (5, 'Harry Potter and the Philosopher.s Stone', 1997, '9780747532743', 5, 4);

INSERT INTO book (id, title, year_published, isbn, author_id, genre_id)
VALUES (6, 'The War of the Worlds', 1898, '9780451530539', 6, 6);

INSERT INTO book (id, title, year_published, isbn, author_id, genre_id)
VALUES (7, 'I, Robot', 1950, '9780553382563', 7, 6);

INSERT INTO book (id, title, year_published, isbn, author_id, genre_id)
VALUES (8, 'Murder on the Orient Express', 1934, '9780062693662', 8, 5);

-- Додаємо читачів з явно заданим ID
INSERT INTO reader (id, first_name, last_name, library_card_number, registered_at)
VALUES (1, 'Alice', 'Johnson', 'LIB123', '2025-01-01');

INSERT INTO reader (id, first_name, last_name, library_card_number, registered_at)
VALUES (2, 'Bob', 'Smith', 'LIB456', '2025-02-01');

INSERT INTO reader (id, first_name, last_name, library_card_number, registered_at)
VALUES (3, 'Charlie', 'Brown', 'LIB789', '2025-03-01');

INSERT INTO reader (id, first_name, last_name, library_card_number, registered_at)
VALUES (4, 'Diana', 'Prince', 'LIB101', '2025-03-15');

INSERT INTO reader (id, first_name, last_name, library_card_number, registered_at)
VALUES (5, 'Edward', 'Norton', 'LIB202', '2025-04-01');
-- Додаємо позики з явно заданим ID
INSERT INTO loan (id, book_id, reader_id, loan_date, return_date)
VALUES (1, 1, 1, '2025-04-01', NULL);

INSERT INTO loan (id, book_id, reader_id, loan_date, return_date)
VALUES (2, 2, 2, '2025-04-05', '2025-04-15');

INSERT INTO loan (id, book_id, reader_id, loan_date, return_date)
VALUES (3, 3, 3, '2025-04-10', NULL);

INSERT INTO loan (id, book_id, reader_id, loan_date, return_date)
VALUES (4, 4, 4, '2025-04-12', NULL);

INSERT INTO loan (id, book_id, reader_id, loan_date, return_date)
VALUES (5, 5, 5, '2025-04-20', NULL);