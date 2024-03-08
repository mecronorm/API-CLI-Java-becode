package org.cogip.cogiprestapi.services;

import org.cogip.cogiprestapi.Exeptions.IdNotFoundException;
import org.cogip.cogiprestapi.enums.CompanyType;
import org.cogip.cogiprestapi.model.Company;
import org.cogip.cogiprestapi.repositories.CompanyRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public String storeCompany(Company company){
        return companyRepository.storeCompany(company);
    }

    public String updateCompany(Integer id, Company company){
        return companyRepository.updateCompany(id,company);
    }

    public String deleteCompany(Integer id){
        return companyRepository.deleteCompany(id);
    }

    public List<Company> findCompanyByType(CompanyType type){
        return companyRepository.findCompanyByType(type);
    }

    public List<Company> findAllCompanies(){
        return companyRepository.findAllCompanies();
    }

    public Company findCompanyById(Integer id) {
        try {
            return companyRepository.findCompanyById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IdNotFoundException("Id number "+id+" not found");
        }
    }
}
