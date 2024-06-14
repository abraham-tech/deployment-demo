package miu.edu.cse.pageabledtodemo.controller;

import lombok.RequiredArgsConstructor;
import miu.edu.cse.pageabledtodemo.dto.BookRequestDto;
import miu.edu.cse.pageabledtodemo.dto.BookResponseDto;
import miu.edu.cse.pageabledtodemo.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.awt.print.Book;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{pageNo}/{pageSize}/{direction}/{sortBy}")
    public ResponseEntity<List<BookResponseDto>> getBooks(
            @PathVariable Integer pageNo,
            @PathVariable Integer pageSize,
            @PathVariable String direction,
            @PathVariable String sortBy){
        Page<BookResponseDto> bookResponseDtoPage = bookService.findAll(pageNo, pageSize, direction, sortBy);
        if(bookResponseDtoPage.getTotalElements() > 0){
            return ResponseEntity.ok(bookResponseDtoPage.getContent());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Optional<List<BookResponseDto>>> createBook(@RequestBody List<BookRequestDto> bookRequestDtos) throws URISyntaxException {
        Optional<List<BookResponseDto>> bookResponseDtos = bookService.addAllBooks(bookRequestDtos);
        if(bookResponseDtos.isPresent()){
            return ResponseEntity.created(new URI("/books")).body(bookResponseDtos);
        }
        return ResponseEntity.noContent().build();
    }
}
