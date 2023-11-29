package ksi.springbooks.services;
import ksi.springbooks.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ksi.springbooks.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;
    public BookService() {
        super();
    }

    public List<Book> findAll() {
        return repository.findAll();
    }
    public List<Book> findAll(String mode) {
        switch (mode)
        {
            case "findByOrderByIdbDesc":
                return repository.findByOrderByIdbDesc();
            case "findByOrderByTitleAsc":
                return repository.findByOrderByTitleAsc();
            case "findByOrderByPublisherNameDesc":
                return repository.findByOrderByPublisherNameDesc();
            case "findByOrderByPublisherNameAscTitleAsc":
                return repository.findByOrderByPublisherNameAscTitleAsc();
            case "findByOrderByCategoryDescriptionAscTitleAsc":
                return repository.findByOrderByCategoryDescriptionAscTitleAsc();
        }
        return repository.findAll();
    }


    public <S extends Book> S save(S entity) {
        return repository.save(entity);
    }

    public Optional<Book> findById(Long aLong) {
        return repository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }
}