package ksi.springbooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ksi.springbooks.models.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByOrderByIdbDesc();
    List<Book> findByOrderByTitleAsc();
    List<Book> findByOrderByPublisherNameDesc();
    List<Book> findByOrderByPublisherNameAscTitleAsc();
    List<Book> findByOrderByCategoryDescriptionAscTitleAsc();



}
