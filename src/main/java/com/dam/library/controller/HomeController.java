package com.dam.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping
public class HomeController {

    @RequestMapping(value = "/*")
    public ResponseEntity<?> pageNotFound() {
        return new ResponseEntity<>("Invalid URL, Page Not Found", HttpStatus.NOT_FOUND);
    }
}

