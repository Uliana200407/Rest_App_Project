package controllers;

import org.springframework.web.client.RestTemplate;
import сomponents.*;
import dto.BookDTO;
import services.BookService;
import mapper.DTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final RestTemplate restTemplate;


    public BookController(BookService bookService,RestTemplate restTemplate) {
        this.bookService = bookService;
        this.restTemplate = restTemplate;

    }

    @GetMapping
    public List< BookDTO > getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        Book book = DTOMapper.convertToEntity(bookDTO);
        BookDTO createdBook = bookService.createBook(book);
        return DTOMapper.convertToDTO(createdBook);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody Optional<BookDTO> updatedBookDTO) {
        BookDTO book = bookService.getBookById(id);

        updatedBookDTO.ifPresent(updated -> {
            if (updated.getTitle() != null) {
                book.setTitle(updated.getTitle());
            }
            if (updated.getAuthor() != null) {
                book.setAuthor(updated.getAuthor());
            }
            if (updated.getYear() != null) {
                book.setYear(updated.getYear());
            }
            if (updated.getGenre() != null) {
                book.setGenre(updated.getGenre());
            }
        });

        BookDTO updatedBook = bookService.updateBook(id, book);
        return updatedBook;
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/send-request")
    public void sendPostRequest() {
        try {
            URL url = new URL("http://localhost:8080/api/books");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Sending JSON data
            String jsonData = "{\"title\":\"New Book Title\",\"author\":\"New Author Name\",\"isbn\":\"1234567890\"}";
            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = jsonData.getBytes("utf-8");
                outputStream.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
