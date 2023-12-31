package сomponents;

import dto.BookDTO;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "books")
public class Book extends BookDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String author;

    public Book(long id, String title, String author, int year, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
    }

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Optional < Integer > getYear() {
        return Optional.ofNullable ( year );
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Optional < String > getGenre() {
        return Optional.ofNullable ( genre );
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @NotBlank
    @Column(nullable = false)
    private Integer year;

    @NotBlank
    @Column(nullable = false)
    private String genre;

}
