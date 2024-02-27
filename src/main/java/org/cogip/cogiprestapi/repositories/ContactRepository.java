package org.cogip.cogiprestapi.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.cogip.cogiprestapi.model.Contact;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactRepository {
  
  private final JdbcTemplate jdbc;
  
  public ContactRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }
  
//  public List<Contact> getAllContacts(){
//    String sql = "SELECT * FROM contact;";
//
//    System.out.println(sql);
//
//    try{
//      return jdbc.query(sql, getContactRowMapper()) ;
//
//    }catch (Exception e){
//      System.out.println("there was an exception");
//    }
//    return null;
//  }
  
  
  public List<Contact> getAllContacts() {
    String sql = "SELECT * FROM contact;";
    
    System.out.println("Executing SQL query: " + sql);
    
    try {
      List<Contact> contacts = jdbc.query(sql, getContactRowMapper());
      System.out.println("Retrieved " + contacts.size() + " contacts from the database");
      return contacts;
    } catch (Exception e) {
      System.out.println("An exception occurred while retrieving contacts: " + e.getMessage());
      e.printStackTrace();
      return null;
    }
  }
  
  
  
  public List<Contact> getContactsByFilters(Integer id, String firstname, String lastname, String phone, Integer companyId){
    StringBuilder sqlBuilder = new StringBuilder();
    sqlBuilder.append("SELECT * FROM contact WHERE 1=1");
    
    List<Object> reqParams = new ArrayList<>();
    
    if (id != null){
      sqlBuilder.append(" AND id= ?");
      reqParams.add(id);
    }
    
    if (firstname != null && !firstname.isEmpty()){
      sqlBuilder.append(" AND firstname = ?");
      reqParams.add(firstname);
    }
    
    if (lastname != null && !lastname.isEmpty()){
      sqlBuilder.append(" AND lastname= ?");
      reqParams.add(lastname);
    }
    
    if (phone != null && !phone.isEmpty()){
      sqlBuilder.append(" AND phone = ?");
      reqParams.add(phone);
    }
    
    if (companyId != null){
      sqlBuilder.append(" AND contact_company_id= ?");
      reqParams.add(companyId);
    }
    return this.jdbc.query(sqlBuilder.toString(), getContactRowMapper(),reqParams.toArray());
  }
  
  public void addContact(Contact contact){
    StringBuilder sqlBuilder = new StringBuilder();
    sqlBuilder.append("INSERT INTO contact (firstname, lastname, phone, email, contact_company_id)");
    sqlBuilder.append(" VALUES (?,?,?,?,?);");
    
    jdbc.update(sqlBuilder.toString(),
            contact.getFirstname(),
            contact.getLastname(),
            contact.getPhone(),
            contact.getEmail(),
            contact.getCompanyId());
  }
  
  public Contact updateContact(Contact contact){
    StringBuilder sqlBuilder = new StringBuilder();
    sqlBuilder.append("UPDATE contact");
    sqlBuilder.append(" SET firstname = ?, lastname = ?, phone = ?, email = ?, contact_company_id = ?");
    sqlBuilder.append(" WHERE id= ?");
    
    this.jdbc.update(sqlBuilder.toString(),
            contact.getFirstname(),
            contact.getLastname(),
            contact.getPhone(),
            contact.getEmail(),
            contact.getCompanyId(),
            contact.getId());
    
    return this.jdbc.queryForObject("SELECT * FROM contact WHERE id=?", getContactRowMapper(), contact.getId());
  }
  
  public void deleteContact(int id){
    this.jdbc.update("DELETE FROM contact WHERE id= ?", id);
  }
  
  private RowMapper<Contact> getContactRowMapper(){
    
    RowMapper<Contact> contactRowMapper = (ResultSet, i) ->{
      
      Contact rowObject = new Contact();
      rowObject.setId(ResultSet.getInt("id"));
      rowObject.setFirstname(ResultSet.getString("firstname"));
      rowObject.setLastname(ResultSet.getString("lastname"));
      rowObject.setPhone(ResultSet.getString("phone"));
      rowObject.setEmail(ResultSet.getString("email"));
      rowObject.setCompanyId(ResultSet.getInt("company_id"));
      
      return rowObject;
    };
    return contactRowMapper;
  }
}
