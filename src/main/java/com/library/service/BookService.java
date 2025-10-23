package com.library.service;

import com.library.exception.NullValueNotAllowedException;
import com.library.infrastructure.repository.BookDAO;
import com.library.model.Book;

import java.sql.SQLException;

public class BookService {

    static BookDAO bookDAO = new BookDAO();

    public static void registerBook(Book book) {
        boolean validated = validateBook(book);

        if (validated){
            try {
                bookDAO.registerBook(book);
                System.out.println("Book has been inserted successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private static boolean validateBook(Book book) {

        boolean validated = true;

        try {
            if (book == null) {
                throw new NullValueNotAllowedException("book");
            }

            if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
                throw new NullValueNotAllowedException("author");
            }

            if (book.getTitle() == null || book.getTitle().isEmpty()) {
                throw new NullValueNotAllowedException("title");
            }

            if (book.getYear() <= 1950) {
                throw new IllegalArgumentException("Book year must be greater than 1950");
            }

        } catch (RuntimeException e) {
            System.err.println("Validation error: " + e.getMessage());
            validated = false;
        }

        return validated;
    }


}
