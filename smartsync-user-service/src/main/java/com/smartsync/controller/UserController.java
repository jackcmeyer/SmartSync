package com.smartsync.controller;

import com.smartsync.model.User;
import com.smartsync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jack Meyer
 *
 * The User Controller
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public UserController() {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/", produces = "application/json")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
    public User getUserById(@PathVariable("id") String id) {

        return this.userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json")
    public User addUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = "application/json")
    public User deleteUser(@PathVariable("id") String id) {
        return this.userService.deleteUser(id);
    }

}
