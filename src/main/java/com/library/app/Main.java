package com.library.app;

import com.library.view.UserInterface;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        boolean continueApp = true;

        while (continueApp){
            UserInterface.mainRouterPages();
        }
    }
}
