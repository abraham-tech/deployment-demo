package miu.edu.cse.pageabledtodemo.repository;

import miu.edu.cse.pageabledtodemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
