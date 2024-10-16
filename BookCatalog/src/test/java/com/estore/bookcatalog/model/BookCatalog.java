package com.estore.bookcatalog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCatalog {

    private String bookId;
    private String bookName;
    private String description;
    private String rating;
}
