package com.estore.bookcatalog.service;

import com.estore.bookcatalog.model.Book;
import com.estore.bookcatalog.model.RatingResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RatingService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getBooksRatingsFallBack",
            commandProperties ={
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="200"),
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="6"),
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="8000"),
            }
    )
    public RatingResponse getRating(String urlRatingService, Book book) throws InterruptedException {
        //Thread.sleep(9000);
        return restTemplate.getForObject(urlRatingService + book.getBookId(), RatingResponse.class);
    }


    public RatingResponse getBooksRatingsFallBack(String urlRatingService, Book book){
        return new RatingResponse("no data","no data","no data");
    }
}
