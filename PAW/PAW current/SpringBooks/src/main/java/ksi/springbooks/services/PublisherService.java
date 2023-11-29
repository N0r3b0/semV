package ksi.springbooks.services;

import ksi.springbooks.models.Publisher;
import ksi.springbooks.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository repository;

    public List<Publisher> findAll() {
        return repository.findAll();
    }

    public <S extends Publisher> S save(S entity) {
        return repository.save(entity);
    }

    public Optional<Publisher> findById(Long aLong) {
        return repository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }
}
