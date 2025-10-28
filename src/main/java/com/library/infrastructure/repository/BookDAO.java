package com.library.infrastructure.repository;

import com.library.infrastructure.database.DBConnection;
import com.library.model.Book;
import com.library.model.Loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {

    public static void setStatusFalse(Loan l) throws SQLException{

        String query = """
                UPDATE books SET available = ? WHERE id = ?
                """;
        try(Connection conn = DBConnection.connection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setBoolean(1, false);
            stmt.setInt(2, l.getBookd_id());
            stmt.executeUpdate();
        }
    }

    public static void setStatusTrue(int idLivro) throws SQLException {

        String query = """
               UPDATE books SET available = ? WHERE id = ?
               """;

        try(Connection conn = DBConnection.connection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setBoolean(1, true);
            stmt.setInt(2, idLivro);
            stmt.executeUpdate();
        } catch (Exception e ){
            System.out.println("ERRO STATUS");
        }
    }

    public void registerBook(Book b) throws SQLException {
        String query = """
                INSERT INTO books (title, author, year)
                VALUES (?, ?, ?)
                """;
        try (Connection conn = DBConnection.connection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, b.getTitle());
            stmt.setString(2, b.getAuthor());
            stmt.setInt(3, b.getYear());
            stmt.executeUpdate();
        }
    }

    public static boolean verifyBook(Loan l) throws SQLException {
        String query = """
                SELECT COUNT(*) FROM books WHERE id = ?
                """;
        try (Connection conn = DBConnection.connection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, l.getBookd_id());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }

        return false;
    }
}

