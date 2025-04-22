package ua.opnu.practice1_template;

import org.springframework.data.jpa.repository.JpaRepository;

interface ReaderRepository extends JpaRepository<Reader, Long> {}
