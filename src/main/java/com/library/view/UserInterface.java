package com.library.view;

import com.library.model.Book;
import com.library.service.BookService;

import java.util.Scanner;

public class UserInterface {

    static Scanner input = new Scanner(System.in);

    public static void mainRouterPages(){
        System.out.println("\n\n====================================");
        System.out.println("    WELCOME TO THE JDBC'S LIBRARY   ");
        System.out.println("====================================");
        System.out.println("1- Register a Book");
        System.out.println("2- Register a Loan");
        System.out.println("3- Book Return");
        System.out.println("4- Query");
        int option = input.nextInt();
        input.nextLine();

        switch (option){

            case 1 ->{
                registerBook();
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
}
