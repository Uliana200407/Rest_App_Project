package services;

import сomponents.Book;
import dto.BookDTO;
import exceptions.ResourceNotFoundException;
import repositories.BookRepository;
import mapper.DTOMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public static List<BookDTO> convertToDTOList(List<Book> books) {
        return books.stream()
                .map(DTOMapper::convertToDTO)
                .collect( Collectors.toList());
    }
    public List< BookDTO > getAllBooks() {
        List < BookDTO > books = bookRepository.findAll();
        return DTOMapper.convertToDTOList( books);
    }


    public BookDTO getBookById(Long id) {
        BookDTO book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException ("Book not found with id: " + id));
        return DTOMapper.convertToDTO(book);
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = DTOMapper.convertToEntity(bookDTO);
        BookDTO createdBook = bookRepository.save(book);
        return DTOMapper.convertToDTO(createdBook);
    }

    public BookDTO updateBook(Long id, BookDTO updatedBookDTO) {
        BookDTO book = getBookById(id);
        book.setTitle(updatedBookDTO.getTitle());
        book.setAuthor(updatedBookDTO.getAuthor());
        book.setYear(updatedBookDTO.getYear());
        book.setGenre(updatedBookDTO.getGenre());
        BookDTO updatedBook = bookRepository.save(book); // Make sure the repository returns a BookDTO object
        return updatedBook;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
