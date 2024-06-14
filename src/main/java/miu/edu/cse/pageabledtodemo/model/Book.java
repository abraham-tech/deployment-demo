package miu.edu.cse.pageabledtodemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String title;
    private String ISBN;
    private LocalDate publishedLocalDate;
    private BigDecimal price;

    public Book(String title, String ISBN, LocalDate publishedLocalDate, BigDecimal price) {
        this.title = title;
        this.ISBN = ISBN;
        this.publishedLocalDate = publishedLocalDate;
        this.price = price;
    }
}
