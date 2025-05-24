package com.crud.service;

import com.crud.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {


    Page<User> getAllUsers(String search, int page, int size, String sortBy, String sortDir);

    public User saveUser(User user);

    public void findById(Long Id);

    public User deleteUser(Long Id);

}
