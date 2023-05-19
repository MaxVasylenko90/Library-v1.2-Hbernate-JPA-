package ua.vasylenko.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.vasylenko.models.Book;
import ua.vasylenko.models.Person;

import java.util.List;
import java.util.Optional;
@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
        Optional<Book> findByName(String name);
        List<Book> findByNameStartingWith(String name);
}
