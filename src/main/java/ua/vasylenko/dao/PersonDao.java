package ua.vasylenko.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.vasylenko.models.Book;
import ua.vasylenko.models.Person;
import ua.vasylenko.services.BooksService;
import ua.vasylenko.services.PeopleService;

import java.util.List;

@Component
public class PersonDao {

}
