package org.cogip.cogiprestapi.services;

import org.cogip.cogiprestapi.model.Company;
import org.cogip.cogiprestapi.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public void storeCompany(Company company){
        companyRepository.storeCompany(company);
    }

    public String updateCompany(Integer id, Company company){
        return companyRepository.updateCompany(id,company);
    }

    public String deleteCompany(Integer id){
        return companyRepository.deleteCompany(id);
    }

    public List<Company> findAllCompanies(){
        return companyRepository.findAllCompanies();
    }

    public Company findCompanyById(Integer id){
        return companyRepository.findCompanyById(id);
    }


}
