package com.techelevator.dao;

import com.techelevator.model.User;

import java.sql.Blob;
import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User getUserById(long id);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role, String email_address);
    
    void updateUserImage(int user_id, String user_image);
}
