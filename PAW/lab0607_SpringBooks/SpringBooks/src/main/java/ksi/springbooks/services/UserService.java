package ksi.springbooks.services;

import ksi.springbooks.models.User;
import ksi.springbooks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public UserService() {
        super();
    }

    public List<User> findAll() {
        return repository.findByOrderByIduDesc();
    }

    public <S extends User> S save(S entity) {
        return repository.save(entity);
    }

    public Optional<User> findById(Long aLong) {
        return repository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    public <S extends User> List<S> findAll(Example<S> example) {
        return repository.findAll(example);
    }
}
