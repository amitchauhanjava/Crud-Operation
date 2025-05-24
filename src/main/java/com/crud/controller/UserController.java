package com.crud.controller;

import com.crud.entity.User;
import com.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * API for retrieve data by given parameter
     * @param search
     * @param page
     * @param size
     * @param sortBy
     * @param sortDir
     * @return
     */
    @GetMapping("/list")
    public Page<User> getAllUsers(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return userService.getAllUsers(search, page, size, sortBy, sortDir);
    }

    /**
     * Save and Update API
     * @param user
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User userData = userService.saveUser(user);
        return ResponseEntity.ok(userData);
    }

    /**
     * Find All by Ids
     * @param Id
     * @return
     */
    @GetMapping("/{Id}")
    public List<User> getUserById(@PathVariable List<Long> Id) {
        return userService.findById(Id);
    }

    @GetMapping("/retrieve/{Id}")
    public ResponseEntity<?> getUserById(@PathVariable Long Id) {

        Optional<User> u = userService.findUserById(Id);

        if (u.isPresent()) {
            return ResponseEntity.ok(u.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with Id "+Id+" not found.");
        }

    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteById(@PathVariable Long Id) {
        userService.deleteUser(Id);
        return ResponseEntity.ok("User with "+Id+" Deleted Successfully");
    }
}
