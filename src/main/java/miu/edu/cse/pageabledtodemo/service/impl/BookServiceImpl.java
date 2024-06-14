package miu.edu.cse.pageabledtodemo.service.impl;

import lombok.RequiredArgsConstructor;
import miu.edu.cse.pageabledtodemo.dto.BookRequestDto;
import miu.edu.cse.pageabledtodemo.dto.BookResponseDto;
import miu.edu.cse.pageabledtodemo.model.Book;
import miu.edu.cse.pageabledtodemo.repository.BookRepository;
import miu.edu.cse.pageabledtodemo.service.BookService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<List<BookResponseDto>> addAllBooks(List<BookRequestDto> bookRequestDtos) {
        //Map from List<BookRequestDto> to List<Book>
        List<Book> bookList = modelMapper.map(bookRequestDtos, new TypeToken<List<Book>>() {}.getType());
        //Save all books
        List<Book> savedBookList = bookRepository.saveAll(bookList);
        //Map from List<Book> to List<BookResponseDto>
        List<BookResponseDto> bookResponseDtos =
                modelMapper.map(savedBookList, new TypeToken<List<BookResponseDto>>() {}.getType());
        return Optional.of(bookResponseDtos);
    }

    @Override
    public Page<BookResponseDto> findAll(int pageNumber, int pageSize, String direction, String sortBy) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.Direction.fromString(direction),
                sortBy
        );
        //It returns a page(mentioned in pageable object) of book entities
        Page<Book> bookPage = bookRepository.findAll(pageable);
        List<BookResponseDto> bookResponseDtoList =
                bookPage.stream()
                .map(book -> modelMapper.map(book, BookResponseDto.class))
                .collect(Collectors.toList());
        System.out.println("Intermediate result: " + bookResponseDtoList);
        return new PageImpl<>(
                bookResponseDtoList,
                pageable,
                bookPage.getTotalElements()
        );
    }
}
