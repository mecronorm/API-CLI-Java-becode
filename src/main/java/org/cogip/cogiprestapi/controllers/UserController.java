package org.cogip.cogiprestapi.controllers;

import org.apache.coyote.BadRequestException;
import org.cogip.cogiprestapi.exceptions.IdDoesNotExistException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.cogip.cogiprestapi.services.UserService;
import org.cogip.cogiprestapi.model.User;

import java.util.List;

@RestController
@RequestMapping ("/users")
public class UserController {
  
  private final UserService userService;
  
  public UserController(UserService userService){
    this.userService = userService;
  }
  
  @GetMapping
  public List<User> getAllUsers(){
    return this.userService.getAllUsers();
  }
  
  @GetMapping ("/{id}")
  public User getUserById(@PathVariable String id) throws EmptyResultDataAccessException {
    try{
      return this.userService.getUserById(id);
    }
    catch (EmptyResultDataAccessException e){
      System.out.println(e.getMessage());
      throw new IdDoesNotExistException();
    }
  }
  
  @PostMapping
  public ResponseEntity<String> addUser(@RequestBody User user){
    this.userService.addUser(user);
    
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("User " + user.getUsername() + " added successfully.");
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable int id,
                                         @RequestBody User user){
    User updatedUser = this.userService.updateUser(id, user);
    
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(updatedUser);
  }
}