package repositories;

import сomponents.Book;
import dto.BookDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository < BookDTO, Long> {

    void delete(Book book);

    void deleteAll(Iterable < ? extends BookDTO > iterable);

    < S extends Book > S save(S s);

    < S extends Book > S saveAndFlush(S s);
}
