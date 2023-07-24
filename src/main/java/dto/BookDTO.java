package dto;

import java.util.Optional;

public class BookDTO {
    public BookDTO() {

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


    private String title;
    private String author;


    public Optional < Integer > getYear() {
        return year;
    }

    public void setYear(Optional < Integer > year) {
        this.year = year;
    }

    public Optional < String > getGenre() {
        return genre;
    }

    public void setGenre(Optional < String > genre) {
        this.genre = genre;
    }

    private Optional<Integer> year;
    private Optional <String> genre;
    private Long id;

    public BookDTO(Long id, String title, String author,Optional < Integer > year, Optional < String > genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
    }


}
