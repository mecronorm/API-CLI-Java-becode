package org.cogip.cogiprestapi.repositories;

import org.cogip.cogiprestapi.enums.CompanyType;
import org.cogip.cogiprestapi.model.Company;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepository{
    private final JdbcTemplate jbcd;
    public CompanyRepository(JdbcTemplate jdbc){
        this.jbcd = jdbc;
    }

    public String storeCompany(Company company){
        String sql = "INSERT INTO company (name, country, vat, type) VALUES (?, ?, ?, ?)";

        jbcd.update(sql, company.getName(), company.getCountry(), company.getVat(), company.getType().name());
        return "Successfully added new company";
    }

    public String updateCompany(Integer id, Company company){
        String sql = "UPDATE company SET name = ?, country = ?, vat = ?, type = ? WHERE id = ?";
        jbcd.update(sql,company.getName(),company.getCountry(),company.getVat(),company.getType().name(),id);
        return "Successfully updated company with id "+id;
    }

    public String deleteCompany(Integer id){
        String sql = "DELETE FROM company WHERE id = ?";
        jbcd.update(sql, id);
        return "Company with id "+id+" has been deleted";
    }

    public List<Company> findAllCompanies(){
        String sql = "SELECT * FROM company";
        return jbcd.query(sql, companyRowMapper());
    }

    public List<Company> findCompanyByType(CompanyType type){
        String sql = "SELECT * FROM company WHERE type = ?";
        return jbcd.query(sql, new Object[]{type.name()}, companyRowMapper());
    }

    public Company findCompanyById(Integer id){
        String sql = "SELECT * FROM company WHERE id = ?";
        return jbcd.queryForObject(sql, new Object[]{id}, companyRowMapper());
    }

    public static RowMapper<Company> companyRowMapper(){
        return (r, i) -> {
            Company rowObject = new Company();
            rowObject.setId(r.getInt("id"));
            rowObject.setName(r.getString("name"));
            rowObject.setCountry(r.getString("country"));
            rowObject.setVat(r.getString("vat"));
            rowObject.setType(CompanyType.valueOf(r.getString("type")));
            rowObject.setTimestamp(r.getTimestamp("timestamp"));

            return rowObject;
        };
    }



}
