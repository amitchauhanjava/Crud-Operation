package com.crud.service;

import com.crud.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Page<User> getAllUsers(String search, int page, int size, String sortBy, String sortDir);

    public User saveUser(User user);

    public Optional<User> findUserById(Long Id);

    public List<User> findById(List<Long> Id);

    public void deleteUser(Long Id);

}
