package com.estore.bookcatalog.controller;

import com.estore.bookcatalog.model.BookCatalog;
import com.estore.bookcatalog.model.BookCatalogResponse;
import com.estore.bookcatalog.model.BookResponse;
import com.estore.bookcatalog.model.RatingResponse;
import com.estore.bookcatalog.service.BookService;
import com.estore.bookcatalog.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/catalog")
public class BooksCatalogController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private BookService bookService;

    ArrayList<BookCatalog> listOfBook = null;

    @GetMapping("/book-details")
    public BookCatalogResponse getBooksRatings() {
        String urlBookService = "http://localhost:8086/book-service/book";
        String urlRatingService ="http://localhost:8080/rating-service/rating/";

        BookCatalogResponse response = null;
        BookResponse books = bookService.getBooks(urlBookService);
        response = new BookCatalogResponse();
        listOfBook = new ArrayList<BookCatalog>();

        books.getBooks().stream().forEach(book->{
            BookCatalog bookCatalog = null;
            RatingResponse rating = null;
            try {
                rating = ratingService.getRating(urlRatingService, book);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bookCatalog = new BookCatalog();
            bookCatalog.setBookId(book.getBookId());
            bookCatalog.setBookName(book.getBookName());
            bookCatalog.setRating(rating.getRating());
            bookCatalog.setDescription(book.getDescription());
            listOfBook.add(bookCatalog);
        });
        response.setCatalogList(listOfBook);
        return response;
    }

}
