package org.cogip.cogiprestapi.services;

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
    return this.userRepository.getAllUsers();
  }
  
  public User getUserById(String id){
    return this.userRepository.getUserById(id);
  }
  
  public void addUser(User user){
    this.userRepository.addUser(user);
  }
  
  public User updateUser(int id, User user){
    return this.userRepository.updateUser(id, user);
  }
  
}