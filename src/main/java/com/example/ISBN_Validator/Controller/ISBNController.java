package com.example.ISBN_Validator.Controller;

import com.example.ISBN_Validator.Logic.ISBNValidator;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ISBNController {

    private final ISBNValidator isbnValidator;

    @Autowired
    public ISBNController(ISBNValidator isbnValidator) {
        this.isbnValidator = isbnValidator;
    }

    @GetMapping("/validate/isbn10/{isbn}")
    public ResponseEntity<String> validateISBN10(@PathVariable String isbn) {
        if (StringUtils.isBlank(isbn)) {
            return ResponseEntity.badRequest().body("ISBN cannot be empty");
        }

        boolean isValid = isbnValidator.isValidISBN10(isbn);
        if (isValid) {
            return ResponseEntity.ok("ISBN-10 is valid");
        } else {
            return ResponseEntity.badRequest().body("ISBN-10 is invalid");
        }
    }

    @GetMapping("/validate/isbn13/{isbn}")
    public ResponseEntity<String> validateISBN13(@PathVariable String isbn) {
        if (StringUtils.isBlank(isbn)) {
            return ResponseEntity.badRequest().body("ISBN cannot be empty");
        }

        boolean isValid = isbnValidator.isValidISBN13(isbn);
        if (isValid) {
            return ResponseEntity.ok("ISBN-13 is valid");
        } else {
            return ResponseEntity.badRequest().body("ISBN-13 is invalid");
        }
    }

}
