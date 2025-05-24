package com.crud.service.impl;

import com.crud.entity.User;
import com.crud.repository.UserRepository;
import com.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSeriveImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    /**
     * Retrieve data with searching sorting etc.
     * @param search
     * @param page
     * @param size
     * @param sortBy
     * @param sortDir
     * @return
     */
    @Override
    public Page<User> getAllUsers(String search, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return search == null || search.isEmpty()
                ? userRepository.findAll(pageable)
                : userRepository.findByNameContainingIgnoreCase(search, pageable);
    }

    /**
     * Save and Update User
     * @param user
     * @return
     */
    @Override
    public User saveUser(User user) {
        if (user.getId() != null) {
            Optional<User> userDetails = userRepository.findById(user.getId());
            if (userDetails.isPresent()) {
                User u = userDetails.get();

                u.setName(user.getName());
                u.setEmail(user.getEmail());
                u.setAge(user.getAge());
                u.setMobile(user.getMobile());
                return userRepository.save(u);
            }
        }

        return userRepository.save(user);
    }

    /**
     * Find user by id
     * @param Id
     * @return
     */
    @Override
    public Optional<User> findUserById(Long Id) {
        return userRepository.findById(Id);
    }

    /**
     * Retrieve all by Id
     * @param Id
     * @return
     */
    @Override
    public List<User> findById(List<Long> Id) {
        return userRepository.findAllById(Id);
    }

    /**
     * Delete By Id
     * @param Id
     * @return
     */
    @Override
    public void deleteUser(Long Id) {
        userRepository.deleteById(Id);
    }
}
