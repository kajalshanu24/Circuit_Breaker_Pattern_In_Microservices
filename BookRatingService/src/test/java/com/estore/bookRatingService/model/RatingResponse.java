package com.estore.bookRatingService.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RatingResponse {

    private String bookId;
    private String bookName;
    private String description;
}
