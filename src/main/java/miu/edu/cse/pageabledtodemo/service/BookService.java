package miu.edu.cse.pageabledtodemo.service;

import miu.edu.cse.pageabledtodemo.dto.BookRequestDto;
import miu.edu.cse.pageabledtodemo.dto.BookResponseDto;
import miu.edu.cse.pageabledtodemo.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<List<BookResponseDto>> addAllBooks(List<BookRequestDto> bookRequestDtos);
    Page<BookResponseDto> findAll(int pageNumber, int pageSize, String direction, String sortBy);
}
