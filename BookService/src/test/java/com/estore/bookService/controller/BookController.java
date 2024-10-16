package com.estore.bookService.controller;

import com.estore.bookService.model.Book;
import com.estore.bookService.model.BookResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/book-service")
public class BookController {

    @GetMapping("/book")
    public BookResponse getBookDetails(String bookName){
        BookResponse response = null;

        response = new BookResponse();
        response.setBookList(getBooks());
        return response;
    }

    public List<Book> getBooks(){
        List<Book> books = Arrays.asList(new Book("J1001","Java","Java Programming language Veriosn X.XX"),
                new Book("C2001","C","C Programming language Version X.XX"),
                new Book("N3001",".Net",".NET Programming language Version X.XX")
        );
        return books;
    }
}
