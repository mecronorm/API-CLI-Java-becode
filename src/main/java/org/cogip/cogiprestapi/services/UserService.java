package org.cogip.cogiprestapi.services;

import org.cogip.cogiprestapi.exceptions.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.cogip.cogiprestapi.model.User;
import org.cogip.cogiprestapi.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
  
  private UserRepository userRepository;
  
  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }
  
  public List<User> getAllUsers(){
    if (userRepository.getAllUsers().isEmpty()) throw new ResultSetEmptyException("There are no users on the database");
    return this.userRepository.getAllUsers();
  }
  
  public User getUserById(String id){
    try {
      Integer.valueOf(id);
      return this.userRepository.getUserById(id);
    }catch (EmptyResultDataAccessException e){
      throw new IdNotFoundException("Id number "+id+" not found");
    }catch (NumberFormatException e){
      throw new InvalidInputException("Id is not a number");
    }
  }
  
  public void addUser(User user){
    try {
      this.userRepository.addUser(user);
    }catch (NullPointerException e){
      String error = userNullError(user);
      throw new MissingParametersException(error);
    }catch (DataIntegrityViolationException e){
      if (e.getMessage().contains("cannot be null")){
        throw new MissingParametersException(userNullError(user));
      }
      if (e.getMessage().contains("Duplicate")) {
        throw new DuplicateValueException(user.getUsername() + " is already taken");
      }
      throw new DuplicateValueException(e.getMessage());
    }
  }
  
  public User updateUser(String id, User user){
    try {
      getUserById(id);
      return this.userRepository.updateUser(Integer.valueOf(id), user);
    }catch (NullPointerException e){
      throw new MissingParametersException(userNullError(user));
    }catch (DataIntegrityViolationException e){
      if (e.getMessage().contains("cannot be null")){
        throw new MissingParametersException(userNullError(user));
      }
      if (e.getMessage().contains("Duplicate")) {
        throw new DuplicateValueException(user.getUsername() + " is already taken");
      }
      throw new DuplicateValueException(e.getMessage());
    }
  }

  public String userNullError(User user){
    String error = "|No input: ";
    if (user.getUsername()==null){
      error = error+="no username | ";
    }
    if (user.getPassword()==null){
      error = error+="no password | ";
    }
    if (user.getRole()==null){
      error =error+="no role | ";
    }
    return error;
  }
}

