package com.library.infrastructure.repository;

import com.library.infrastructure.database.DBConnection;
import com.library.model.Loan;
import com.library.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static void registerUser(User u) throws SQLException {
        String query = """
                INSERT INTO users
                (name, email, phone)
                VALUES (?, ?, ?)
                """;
        try(Connection conn = DBConnection.connection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPhone());
            stmt.executeUpdate();
        }
    }

    public static boolean verifyUser(Loan l) throws SQLException{

        String query = """
                SELECT COUNT(*) FROM users WHERE id = ?
                """;
        try(Connection conn = DBConnection.connection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, l.getUser_id());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        return false;

    }
}
