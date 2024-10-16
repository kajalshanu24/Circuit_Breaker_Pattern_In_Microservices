package com.estore.bookService.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class BookResponse {

    List<Book> bookList;
}
