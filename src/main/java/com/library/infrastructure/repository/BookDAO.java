package com.library.infrastructure.repository;

import com.library.infrastructure.database.DBConnection;
import com.library.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDAO {

    public void registerBook(Book b ) throws SQLException {
        String query = """
                INSERT INTO books (title, author, year) 
                VALUES (?, ?, ?)
                """;
        try(Connection conn = DBConnection.connection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, b.getTitle());
            stmt.setString(2, b.getAuthor());
            stmt.setInt(3, b.getYear());
            stmt.executeUpdate();
        }
    }
}
