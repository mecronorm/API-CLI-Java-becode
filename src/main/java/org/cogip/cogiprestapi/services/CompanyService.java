package org.cogip.cogiprestapi.services;

import org.cogip.cogiprestapi.Exeptions.*;
import org.cogip.cogiprestapi.enums.CompanyType;
import org.cogip.cogiprestapi.model.Company;
import org.cogip.cogiprestapi.repositories.CompanyRepository;
import org.springframework.boot.json.JsonParseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public String storeCompany(Company company){
        try {
            return companyRepository.storeCompany(company);
        }catch (NullPointerException e){
            String error = companyNullError(company);
            throw new MissingParametersException(error);
        }catch (DataIntegrityViolationException e){
            if (e.getMessage().contains("cannot be null")) throw new MissingParametersException(companyNullError(company));
            if (e.getMessage().contains("Duplicate"))throw new DuplicateValueException(company.getVat()+" is a duplicate, only give in an original vat number");
            throw new DuplicateValueException(e.getMessage());
        }
    }

    public String updateCompany(String id, Company company){
        try {
            findCompanyById(id);
            return companyRepository.updateCompany(Integer.valueOf(id), company);
        }catch (NullPointerException e) {
            throw new MissingParametersException(companyNullError(company));
        }catch (DataIntegrityViolationException e){
            if (e.getMessage().contains("cannot be null")) throw new MissingParametersException(companyNullError(company));
            if (e.getMessage().contains("Duplicate"))throw new DuplicateValueException(company.getVat()+" is a duplicate, only give in an original vat number");
            throw new DuplicateValueException(e.getMessage());
        }
    }


    public String deleteCompany(String id){
        findCompanyById(id);
        return companyRepository.deleteCompany(Integer.valueOf(id));
    }

    public List<Company> findCompanyByType(String type){
        try {
            CompanyType testType = CompanyType.valueOf(type);
            if (companyRepository.findCompanyByType(testType).isEmpty())
                throw new ResultSetEmptyException("No data found with type: " + testType.name() + " in the database");
            return companyRepository.findCompanyByType(testType);
        }catch (IllegalArgumentException e){
            throw new InvalidInputException("Input is not "+ Arrays.toString(CompanyType.values()));
        }
    }

    public List<Company> findAllCompanies(){
        if (companyRepository.findAllCompanies().isEmpty()) throw new ResultSetEmptyException("No data found in the database");
        return companyRepository.findAllCompanies();
    }

    public Company findCompanyById(String id) {
        try {
            return companyRepository.findCompanyById(Integer.valueOf(id));
        } catch (EmptyResultDataAccessException e) {
            throw new IdNotFoundException("Id number "+id+" not found");
        } catch (NumberFormatException e){
            throw new InvalidInputException("Input is not a number");
        }
    }

    private String companyNullError(Company company){
        String error = "|No Input: ";
        if (company.getName()==null||company.getName().isEmpty()){
            error=error+"name cannot be null | ";
        }
        if (company.getCountry()==null||company.getCountry().isEmpty()){
            error=error+"country cannot be null | ";
        }
        if (company.getType()==null){
            error=error+"type cannot be null | ";
        }
        if (company.getVat()==null||company.getVat().isEmpty()){
            error=error+"vat cannot be null | ";
        }
        return error;
    }
}
