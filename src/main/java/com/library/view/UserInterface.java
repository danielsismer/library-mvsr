package com.library.view;

import com.library.model.Book;
import com.library.model.Loan;
import com.library.model.User;
import com.library.service.BookService;
import com.library.service.LoanService;
import com.library.service.UserService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {

    static Scanner input = new Scanner(System.in);

    public static void mainRouterPages() throws SQLException {
        System.out.println("\n\n====================================");
        System.out.println("    WELCOME TO THE JDBC'S LIBRARY   ");
        System.out.println("====================================");
        System.out.println("1- Register a Book");
        System.out.println("2- Register a User");
        System.out.println("3- Register a Loan");
        System.out.println("4- Register a Return");
        System.out.print("Choose an option: ");
        int option = input.nextInt();
        input.nextLine();

        switch (option){

            case 1 ->{
                registerBook();
            }

            case 2 ->{
                registerUser();
            }

            case 3 ->{
                registerLoan();
            }

            case 4 ->{
                returnLoan();
            }
        }
    }

    public static void registerBook(){
        System.out.println("====================================");
        System.out.println("           REGISTER A BOOK          ");
        System.out.println("====================================");
        System.out.print(" -> Enter the book's title: ");
        String title = input.nextLine();
        System.out.print(" -> Enter the book's author: ");
        String author = input.nextLine();
        System.out.print(" -> Enter the book's year: ");
        int year = input.nextInt();
        input.nextLine();
        BookService.registerBook(new Book(title, author, year));
    }

    public static void registerUser(){
        System.out.println("====================================");
        System.out.println("           REGISTER A USER          ");
        System.out.println("====================================");
        System.out.print(" -> Enter the user's name: ");
        String name = input.nextLine();
        System.out.print(" -> Enter the user's email: ");
        String email = input.nextLine();
        System.out.print(" -> Enter the user's phone: ");
        String phone = input.nextLine();
        UserService.registerUser(new User(name, email, phone));
    }

    public static void registerLoan() throws SQLException {
        System.out.println("====================================");
        System.out.println("           REGISTER A LOAN          ");
        System.out.println("====================================");
        System.out.print(" -> Enter the book's ID: ");
        int book_id = input.nextInt();
        input.nextLine();
        System.out.print(" -> Enter the user's ID: ");
        int user_id = input.nextInt();
        LoanService.registerLoan(new Loan(book_id, user_id));
    }

    public static void returnLoan() throws SQLException {
        System.out.println("====================================");
        System.out.println("           RETURN A LOAN          ");
        System.out.println("====================================");
        System.out.print(" -> Enter the loan's ID: ");
        int loan_id = input.nextInt();
        input.nextLine();
        System.out.println("Enter the return's date: ");
        Date date = Date.valueOf(input.nextLine());
        LoanService.returnBook(new Loan(loan_id, date));
    }
}
