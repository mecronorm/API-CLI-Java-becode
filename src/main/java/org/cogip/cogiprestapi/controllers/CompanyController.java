package org.cogip.cogiprestapi.controllers;

import org.cogip.cogiprestapi.model.Company;
import org.cogip.cogiprestapi.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping
    public void storeCompany(@RequestBody Company company){
        companyRepository.storeCompany(company);
    }

    @GetMapping
    public List<Company> findCompanies(){
        return companyRepository.findAllCompanies();
    }

    @GetMapping("/{id}")
    public Company findCompanyById(@PathVariable("id") String id){
        return companyRepository.findCompanyById(Integer.valueOf(id));
    }

}
