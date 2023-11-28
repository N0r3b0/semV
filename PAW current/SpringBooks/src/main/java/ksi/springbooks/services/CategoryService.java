package ksi.springbooks.services;

import ksi.springbooks.models.Category;
import ksi.springbooks.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    public CategoryService() {
        super();
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public <S extends Category> S save(S entity) {
        return repository.save(entity);
    }

    public Optional<Category> findById(Long aLong) {
        return repository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }
}