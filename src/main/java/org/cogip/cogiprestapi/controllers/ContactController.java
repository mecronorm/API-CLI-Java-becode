package org.cogip.cogiprestapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.cogip.cogiprestapi.model.Contact;
import org.cogip.cogiprestapi.services.ContactService;

import java.util.List;

@RestController
@RequestMapping ("/contacts")
public class ContactController {
  
  private final ContactService contactService;
  
  public ContactController(ContactService contactService){
    this.contactService = contactService;
  }
  
  @GetMapping
  public List<Contact> getAllContact() {
    return this.contactService.getAllContacts();
  }
  
  @GetMapping ("/search")
  public List<Contact> getContactsByFilters(
          @RequestParam (required = false) String id,
          @RequestParam (required = false) String firstname,
          @RequestParam (required = false) String lastname,
          @RequestParam (required = false) String phone,
          @RequestParam (required = false) String companyId
  ){
    return this.contactService.getContactsByFilters(id, firstname, lastname, phone, companyId);
  }
  
  @PostMapping
  public ResponseEntity<String> addContact(@RequestBody Contact contact){
    this.contactService.addContact(contact);
    
    return ResponseEntity
            .status(HttpStatus.OK)
            .body("Contact " + contact.getFirstname() + " " + contact.getLastname() + " was successfully added.");
  }
  
  @PutMapping ("/{id}")
  public ResponseEntity<Contact> updateContact(@PathVariable("id") String id, @RequestBody Contact contact){
    Contact updatedContact = this.contactService.updateContact(id, contact);
    
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(updatedContact);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteContact(@PathVariable("id") int id){
    this.contactService.deleteContact(id);
    
    return ResponseEntity
            .status(HttpStatus.OK)
            .body("The contact with id " + id + " was successfully deleted.");
  }
}

