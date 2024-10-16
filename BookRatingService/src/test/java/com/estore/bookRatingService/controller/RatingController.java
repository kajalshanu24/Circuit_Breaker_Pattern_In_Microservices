package com.estore.bookRatingService.controller;

import com.estore.bookRatingService.model.RatingResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rating-service")
public class RatingController {

    @GetMapping("/rating/{bookId}")
    public RatingResponse getRatingForBook(@PathVariable("bookId") String bookId) {

        System.out.println("Rating service calling...");
        List<RatingResponse> ratings = getDummyRatings();
        List<RatingResponse> rating=    ratings.stream().filter(rate->{
            if (rate.getBookId().equals(bookId))
                return true;
            else
                return false;
        }).collect(Collectors.toList());
        return rating.get(0);
    }


    private  List<RatingResponse> getDummyRatings() {

        List<RatingResponse> ratings = Arrays.asList(
                new RatingResponse("J1001","Java","5"),
                new RatingResponse("C2001","C","4"),
                new RatingResponse("N3001",".NET","3")
        );
        return ratings;
    }
}
