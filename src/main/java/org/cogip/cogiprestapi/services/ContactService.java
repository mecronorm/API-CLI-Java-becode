package org.cogip.cogiprestapi.services;

import org.cogip.cogiprestapi.exceptions.IdNotFoundException;
import org.cogip.cogiprestapi.exceptions.InvalidInputException;
import org.cogip.cogiprestapi.exceptions.MissingParametersException;
import org.cogip.cogiprestapi.exceptions.ResultSetEmptyException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.cogip.cogiprestapi.model.Contact;
import org.cogip.cogiprestapi.repositories.ContactRepository;

import java.util.List;

@Service
public class ContactService {
  
  private final ContactRepository contactRepository;
  
  public ContactService(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }
  
  public List<Contact> getAllContacts(){
    if (this.contactRepository.getAllContacts().isEmpty()) throw new ResultSetEmptyException("No contacts in the database");
    return this.contactRepository.getAllContacts();
  }
  
  public List<Contact> getContactsByFilters(String id, String firstname, String lastname, String phone, String companyId){
    try {
      if (this.contactRepository.getContactsByFilters(Integer.valueOf(id), firstname, lastname, phone, Integer.valueOf(companyId)).isEmpty()) throw new ResultSetEmptyException("No contacts have been found with these params");
      return this.contactRepository.getContactsByFilters(Integer.valueOf(id), firstname, lastname, phone, Integer.valueOf(companyId));
    }catch (NumberFormatException e){
      if (!companyId.matches("-?\\d+(\\.\\d+)?")&&!id.matches("-?\\d+(\\.\\d+)?")){
        throw new InvalidInputException(id+" and "+companyId+" are not valid numbers");
      }
      if (!companyId.matches("-?\\d+(\\.\\d+)?")){
        throw new InvalidInputException(companyId+" is not a valid number");
      }
      if (!id.matches("-?\\d+(\\.\\d+)?")){
        throw new InvalidInputException(id+" is not a valid number");
      }
      throw new InvalidInputException("Unknown number error");
    }
  }
  
  public void addContact(Contact contact){
    try{
      this.contactRepository.addContact(contact);
    }catch (DataIntegrityViolationException e){
      if (e.getMessage().contains("cannot be null"))throw new MissingParametersException(contactNullException(contact));
      if (e.getMessage().contains("foreign key constraint"))throw new IdNotFoundException("company with id "+contact.getCompanyId()+" not found");
    }

  }
  
  public Contact updateContact(String id, Contact contact){
    try {
      return this.contactRepository.updateContact(Integer.valueOf(id), contact);
    }catch (DataIntegrityViolationException e){
      if (e.getMessage().contains("cannot be null"))throw new MissingParametersException(contactNullException(contact));
      if (e.getMessage().contains("foreign key constraint"))throw new IdNotFoundException("company with id "+contact.getCompanyId()+" not found");
      throw new InvalidInputException("unknown data integrity error");
    }catch(NumberFormatException e){
      throw new InvalidInputException("Id is not a number");
    }catch (EmptyResultDataAccessException e){
      throw new IdNotFoundException("id "+id+" not found");
    }

  }
  
  public void deleteContact(int id){
    try {
      this.contactRepository.deleteContact(id);
    }catch(NumberFormatException e){
      throw new InvalidInputException("Id is not a number");
    }catch (EmptyResultDataAccessException e){
      throw new IdNotFoundException("id "+id+" not found");
    }
  }

  private String contactNullException(Contact contact){
    String error = "|Missing input: ";
    if (contact.getFirstname()==null) error = error+"firstname cannot be empty | ";
    if (contact.getLastname()==null) error=error+"lastname cannot be empty | ";
    if (contact.getEmail()==null) error=error+"email cannot be empty | ";
    if (contact.getPhone()==null) error=error+"phone cannot be empty | ";
    if (contact.getCompanyId()==0) error=error+"company_id cannot be empty | ";
    return error;
  }
}