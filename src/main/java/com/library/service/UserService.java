package com.library.service;

import com.library.exception.NullValueNotAllowedException;
import com.library.infrastructure.repository.UserDAO;
import com.library.model.User;

import java.sql.SQLException;

public class UserService {

    public static void registerUser(User u){
        boolean validate = validateUser(u);

        if(validate){
            try{
                UserDAO.registerUser(u);
                System.out.println("User has been inserted successfully!");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static boolean validateUser(User u){

        try{
            if (u == null){
                throw new NullValueNotAllowedException("user");
            }

            if (u.getEmail().isEmpty() || u.getEmail() == null){
                throw new NullValueNotAllowedException("email");
            }

            if (u.getName().isEmpty() || u.getName() == null){
                throw new NullValueNotAllowedException("name");
            }

            if (u.getPhone().isEmpty() || u.getPhone() == null){
                throw new NullValueNotAllowedException("phone");
            }
        } catch (RuntimeException r){
            r.printStackTrace();
            return false;
        }

        return true;
    }
}
