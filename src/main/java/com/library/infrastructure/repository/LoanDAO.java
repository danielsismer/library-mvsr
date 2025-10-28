package com.library.infrastructure.repository;

import com.library.infrastructure.database.DBConnection;
import com.library.model.Loan;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {

    public static void registerLoan(Loan l) throws SQLException{
        String query = """
                INSERT INTO loans (book_id, user_id) VALUES (?, ?);
                """;
        try(Connection conn = DBConnection.connection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, l.getBookd_id());
            stmt.setInt(2, l.getUser_id());
            stmt.executeUpdate();
        }
    }

    public static boolean verifyLoan(Loan l) throws SQLException {

        String query = """
                SELECT COUNT(*) FROM loans WHERE id = ?
                """;
        try(Connection conn = DBConnection.connection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, l.getId());

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int amount = rs.getInt(1);
                return amount < 0;
            }
        }

        return false;
    }

    public static ArrayList<Loan> select() throws SQLException {
        ArrayList<Loan> loans = new ArrayList<>();
        String query = """
                SELECT id, book_id, user_id, loan_date, return_date FROM loans WHERE 1=1
                """;
        try(Connection conn = DBConnection.connection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                int idBook = rs.getInt("book_id");
                int idUser = rs.getInt("user_id");
                Date loanDate = rs.getDate("loan_date");
                Date returnLoan = rs.getDate("return_date");
                loans.add(new Loan(id, idBook, idUser, loanDate, returnLoan));
            }
        }

        return loans;
    }

    public static void addDateReturn(Loan loan) throws SQLException{
        String query = """
                UPDATE loans SET return_date = ? WHERE id = ?
                """;

        try(Connection conn = DBConnection.connection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setDate(1, loan.getReturn_date());
            stmt.setInt(2, loan.getId());
            stmt.executeUpdate();
        }
    }

    public static int getIdLivro(Loan l) throws SQLException{

        String query = """
                SELECT book_id FROM loans WHERE id = ?
                """;
        try(Connection conn = DBConnection.connection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, l.getId());
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return rs.getInt(1);
            }
        }
        return 0;
    }
}
