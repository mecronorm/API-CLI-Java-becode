package org.cogip.cogiprestapi.controllers;

import org.cogip.cogiprestapi.dto.CompanyDTO;
import org.cogip.cogiprestapi.exceptions.InvalidInputException;
import org.cogip.cogiprestapi.model.Company;
import org.cogip.cogiprestapi.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping
    public String storeCompany(@RequestBody Company company){
        try {
            return companyService.storeCompany(company);
        }catch (HttpMessageNotReadableException e){
            throw new InvalidInputException("Enum input is wrong");
        }
    }

    @GetMapping
    public List<Company> findCompanies(){
        return companyService.findAllCompanies();
    }

    @GetMapping("/info")
    public List<CompanyDTO> findCompaniesDTO(){
        return companyService.findAllCompaniesDTO();
    }

    @GetMapping("/type/{type}")
    public List<Company> getCompanyByType(@PathVariable("type")String type){
        return companyService.findCompanyByType(type);
    }

    @GetMapping("/{id}")
    public Company findCompanyById(@PathVariable("id") String id){
        return companyService.findCompanyById(id);
    }



    @PutMapping("/{id}")
    public String updateCompany(@PathVariable("id") String id,@RequestBody Company company){
        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable("id") String id){
        return companyService.deleteCompany(id);
    }

}
