package Tests;

import controllers.BookController;
import сomponents.Book;
import dto.BookDTO;
import repositories.BookRepository;
import services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UnitTests {
    @Mock
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBookById() {
        Long bookId = 1L;
        Book book = new Book();
        when(bookService.getBookById(bookId)).thenReturn(book);

        BookDTO result = bookController.getBookById(bookId);

        assertEquals(book, result);
        verify(bookService, times(1)).getBookById(bookId);
    }

    @Test
    void createBook() {
        Book book = new Book();
        when(bookService.createBook(any(Book.class))).thenReturn(book);

        BookDTO result = bookController.createBook(book);

        assertEquals(book, result);
        verify(bookService, times(1)).createBook(book);
    }

    @Test
    void updateBook() {
        Long bookId = 1L;
        Optional <BookDTO> updatedBookDTO = Optional.of(new BookDTO());
        BookDTO book = new BookDTO();
        when(bookService.getBookById(bookId)).thenReturn(book);

        BookDTO expectedUpdatedBook = new BookDTO();
        when(bookService.updateBook(bookId, book)).thenReturn(expectedUpdatedBook);

        BookDTO result = bookController.updateBook(bookId, updatedBookDTO);

        assertEquals(expectedUpdatedBook, result);
        verify(bookService, times(1)).getBookById(bookId);
        verify(bookService, times(1)).updateBook(bookId, book);
    }

    @Test
    void deleteBook() {
        Long bookId = 1L;
        ResponseEntity<Void> expectedResponse = ResponseEntity.noContent().build();
        doNothing().when(bookService).deleteBook(bookId);

        ResponseEntity<Void> response = bookController.deleteBook(bookId);

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        verify(bookService, times(1)).deleteBook(bookId);
    }

}