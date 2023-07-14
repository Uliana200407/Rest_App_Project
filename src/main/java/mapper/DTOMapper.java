package mapper;
import Components.Book;
import dto.BookDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class DTOMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static BookDTO convertToDTO(BookDTO book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public static Book convertToEntity(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    public static List<BookDTO> convertToDTOList(List < BookDTO > books) {
        return books.stream()
                .map(DTOMapper::convertToDTO)
                .collect( Collectors.toList());
    }
}
