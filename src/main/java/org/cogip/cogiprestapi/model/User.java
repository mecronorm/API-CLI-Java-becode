package org.cogip.cogiprestapi.model;

import jakarta.persistence.*;
import org.cogip.cogiprestapi.enums.UserRole;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private int id;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, name = "role")
  private UserRole role;
  
  public int getId(){
    return this.id;
  }
  
  public void setId(int id){
    this.id = id;
  }
  
  
  public String getUsername() {
    return username;
  }
  
  public void setUserName(String username) {
    this.username = username;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public UserRole getRole() {
    return role;
  }
  
  public void setRole(UserRole role) {
    this.role = role;
  }
}


