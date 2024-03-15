package org.cogip.cogiprestapi.repositories;

import org.cogip.cogiprestapi.dto.CompanyDTO;
import org.cogip.cogiprestapi.enums.CompanyType;
import org.cogip.cogiprestapi.model.Company;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<CompanyDTO> findAllCompaniesDto(){
        String sql = "SELECT c.name, c.vat, i.invoice_number, con.firstname FROM company c LEFT JOIN invoice i ON c.id = i.invoice_company_id LEFT JOIN contact con on c.id = con.company_id";
        return jbcd.query(sql, new ResultSetExtractor<List<CompanyDTO>>() {
            @Override
            public List<CompanyDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<CompanyDTO> list = new ArrayList<>();
                rs.next();
                while (!rs.isAfterLast()){
                    CompanyDTO c = new CompanyDTO();
                    c.setName(rs.getString("name"));
                    c.setVat(rs.getString("vat"));
                    List<String> invoices = new ArrayList<>();
                    List<String> contacts = new ArrayList<>();
                    while (c.getVat().equals(rs.getString("vat"))){
                        String invoiceNumber = rs.getString("invoice_number");
                        if (invoiceNumber!=null){
                            invoices.add(invoiceNumber);
                        }

                        String contactName = rs.getString("firstname");
                        if (contactName != null){
                            contacts.add(contactName);
                        }
                        rs.next();
                        if (rs.isAfterLast())break;
                    }
                    c.setInvoices(invoices);
                    c.setContacts(contacts);
                    list.add(c);
                }
                return list;
            }
        });
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
