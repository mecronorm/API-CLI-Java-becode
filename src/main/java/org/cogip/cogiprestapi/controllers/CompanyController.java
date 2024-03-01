package org.cogip.cogiprestapi.controllers;

import org.cogip.cogiprestapi.enums.CompanyType;
import org.cogip.cogiprestapi.model.Company;
import org.cogip.cogiprestapi.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/add")
    public String storeCompany(@RequestBody Company company){
        return companyService.storeCompany(company);
    }

    @GetMapping("/get")
    public List<Company> findCompanies(){
        return companyService.findAllCompanies();
    }

    @GetMapping("/get/{id}")
    public Company findCompanyById(@PathVariable("id") String id){
        return companyService.findCompanyById(Integer.valueOf(id));
    }

    @GetMapping("/get/type/{type}")
    public List<Company> getCompanyByType(@PathVariable("type")CompanyType type){
        return companyService.findCompanyByType(type);
    }

    @PutMapping("/edit/{id}")
    public String updateCompany(@PathVariable("id") String id,@RequestBody Company company){
        return companyService.updateCompany(Integer.valueOf(id), company);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCompany(@PathVariable("id") String id){
        return companyService.deleteCompany(Integer.valueOf(id));
    }

}
