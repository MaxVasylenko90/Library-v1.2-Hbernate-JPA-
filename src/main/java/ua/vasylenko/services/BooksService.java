package ua.vasylenko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vasylenko.models.Book;
import ua.vasylenko.models.Person;
import ua.vasylenko.repositories.BooksRepository;
import ua.vasylenko.repositories.PeopleRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public Page<Book> findAll(int pageNumber, int pageSize, String sortedField) {
        return booksRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortedField)));
    }

    @Transactional
    public void addNewBook(Book book) {
        booksRepository.save(book);
    }

    public Book getBook(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public Optional<Book> getBook(String name) {
        return booksRepository.findByName(name);
    }


    public Optional<Person> getPerson(Book book) {
        return Optional.ofNullable(book.getPerson());
    }

    @Transactional
    public void assignPerson(int bookId, int personId) {
        Book book = booksRepository.findById(bookId).orElse(null);
        book.setPerson(peopleRepository.findById(personId).orElse(null));
        book.setTakenAt(new Date());
    }

    @Transactional
    public void releaseBook(int bookId) {
        Book book = booksRepository.findById(bookId).orElse(null);
        book.setPerson(null);
        book.setTakenAt(null);
    }

    @Transactional
    public void deleteBook(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void update(int bookId, Book book) {
        Book bookFromDb = getBook(bookId);
        book.setId(bookId);
        book.setPerson(bookFromDb.getPerson());
        booksRepository.save(book);
    }

    public Object findByNameStartingWith(String search) {
        return booksRepository.findByNameStartingWith(search);
    }
}

