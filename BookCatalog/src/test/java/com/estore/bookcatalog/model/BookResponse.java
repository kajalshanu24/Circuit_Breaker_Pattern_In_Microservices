package com.estore.bookcatalog.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookResponse {

    List<Book> books;
}
