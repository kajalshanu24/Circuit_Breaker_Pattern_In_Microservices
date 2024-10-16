package com.estore.bookcatalog.service;

import com.estore.bookcatalog.model.Book;
import com.estore.bookcatalog.model.BookResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class BookService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getBooksFallBack",
            commandProperties ={
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000"),
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="6"),
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="8000"),
            }
    )
    public BookResponse getBooks(String urlBookService) {
        return restTemplate.getForObject(urlBookService, BookResponse.class);
    }


    public BookResponse getBooksFallBack(String urlBookService) {
        return new BookResponse(Arrays.asList(new Book("no data","no data","no data")));
    }
}
