package com.library.service;

import com.library.exception.PrimaryKeyCantBeNull;
import com.library.infrastructure.database.DBConnection;
import com.library.infrastructure.repository.BookDAO;
import com.library.infrastructure.repository.LoanDAO;
import com.library.infrastructure.repository.UserDAO;
import com.library.model.Book;
import com.library.model.Loan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanService {

    public static void registerLoan(Loan l) throws SQLException {
        boolean validate = validateLoan(l);
        boolean existsUser = verifyIfExistsUser(l);
        boolean existsBook = verifyIfExistsBook(l);

        if (validate && existsBook && existsUser){
            try(Connection conn = DBConnection.connection()){
                conn.setAutoCommit(false);
                try{
                    LoanDAO.registerLoan(l);
                    BookDAO.setStatusFalse(l);
                    conn.commit();
                    System.out.println("Loan has been inserted successfully!");
                } catch (SQLException e ){
                    e.printStackTrace();
                    conn.rollback();
                    System.out.println("Curented an error during the transaction");
                } finally {
                    conn.setAutoCommit(true);
                }
            }
        }

        else if (validate && !existsBook && existsUser){
            System.out.println("Book's ID not found");
        }

        else if (validate && existsBook && !existsUser){
            System.out.println("User's ID not found");
        }

        else {
            System.out.println("Something went wrong");
        }
    }

    public static void returnBook (Loan loan) throws SQLException {
            boolean verifyLoanID = verifyLoanID(loan);
            boolean verififyDateReturn = verifyDateReturn(loan);
            int idLivro = LoanDAO.getIdLivro(loan);

            if(!verifyLoanID && !verififyDateReturn){
                try(Connection conn = DBConnection.connection()){
                    conn.setAutoCommit(false);
                    try{
                        BookDAO.setStatusTrue(idLivro);
                        LoanDAO.addDateReturn(loan);
                        System.out.println("Loan returned sucessfully!!!");
                        conn.commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                        conn.rollback();
                    } finally{
                        conn.setAutoCommit(true);
                    }
                }
            }
    }

    private static boolean validateLoan(Loan l) {

        try{
            if(l.getBookd_id() <= 0 ){
                throw new PrimaryKeyCantBeNull("book's ID");
            }

            if(l.getUser_id() <= 0 ){
                throw new PrimaryKeyCantBeNull("user's ID");
            }
        } catch (RuntimeException r ){
            r.printStackTrace();
            return false;
        }

        return true;

    }

    private static boolean verifyIfExistsUser(Loan l) throws SQLException { return UserDAO.verifyUser(l); }
    private static boolean verifyIfExistsBook(Loan l ) throws SQLException{ return BookDAO.verifyBook(l); }
    private static boolean verifyLoanID(Loan l) throws SQLException { return LoanDAO.verifyLoan(l); }

    private static boolean verifyDateReturn(Loan l) throws SQLException {
       ArrayList<Loan> loans = LoanDAO.select();
        for(Loan lns : loans ){
            if (lns.getId() == l.getId()){
                if(l.getReturn_date().after(lns.getLoan_date())){
                    return true;
                }
            }
        }
        return false;
    }
}
