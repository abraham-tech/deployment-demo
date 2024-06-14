package miu.edu.cse.pageabledtodemo;

import lombok.RequiredArgsConstructor;
import miu.edu.cse.pageabledtodemo.dto.BookRequestDto;
import miu.edu.cse.pageabledtodemo.dto.BookResponseDto;
import miu.edu.cse.pageabledtodemo.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class PageabledtodemoApplication implements CommandLineRunner {

    private final BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(PageabledtodemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BookRequestDto dto1 = new BookRequestDto(
                "title#1",
                "1234",
                LocalDate.of(2020, 1, 2),
                BigDecimal.valueOf(10.25)
        );
        BookRequestDto dto2 = new BookRequestDto(
                "title#2",
                "1235",
                LocalDate.of(2021, 1, 1),
                BigDecimal.valueOf(11.25)
        );
        BookRequestDto dto3 = new BookRequestDto(
                "title#3",
                "1236",
                LocalDate.of(2020, 1, 2),
                BigDecimal.valueOf(20.25)
        );
        BookRequestDto dto4 = new BookRequestDto(
                "title#4",
                "1237",
                LocalDate.of(2024, 1, 2),
                BigDecimal.valueOf(9.25)
        );
        BookRequestDto dto5 = new BookRequestDto(
                "title#5",
                "1238",
                LocalDate.of(1998, 1, 2),
                BigDecimal.valueOf(101.25)
        );
        BookRequestDto dto6 = new BookRequestDto(
                "title#6",
                "1239",
                LocalDate.of(2019, 1, 2),
                BigDecimal.valueOf(8.25)
        );
        //Save all books
        System.out.println(
                bookService.addAllBooks(
                        List.of(dto1, dto2, dto3, dto4, dto5, dto6)
                )
        );
        System.out.println("page# 0, page size# 2");
        bookService.findAll(0, 2, "asc", "publishedLocalDate")
                .forEach(System.out::println);
        System.out.println("page# 1, page size# 3");
        bookService.findAll(1, 3, "asc", "title")
                .forEach(System.out::println);
    }
}
