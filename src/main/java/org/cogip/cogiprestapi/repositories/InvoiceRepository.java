package org.cogip.cogiprestapi.repositories;

import org.cogip.cogiprestapi.dto.InvoiceDTO;
import org.cogip.cogiprestapi.enums.CompanyType;
import org.cogip.cogiprestapi.enums.InvoiceCurrency;
import org.cogip.cogiprestapi.enums.InvoiceStatus;
import org.cogip.cogiprestapi.enums.InvoiceType;
import org.cogip.cogiprestapi.model.Invoice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InvoiceRepository {

    private final JdbcTemplate jdbc;
    public InvoiceRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public String createInvoice(Invoice invoice){
        String sql = "INSERT INTO invoice (invoice_company_id, invoice_contact_id, invoice_number, value, currency, type, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbc.update(sql, invoice.getInvoice_company_id(), invoice.getInvoice_contact_id(), invoice.getInvoice_number(), invoice.getValue(), invoice.getCurrency().name(), invoice.getType().name(), invoice.getStatus().name());

        return "Successfully added invoice";
    }

    public String updateInvoice(Integer id, Invoice invoice){
        String sql = "UPDATE invoice SET invoice_company_id = ?, invoice_contact_id = ?, invoice_number = ?, value = ?, currency = ?, type = ?, status = ? WHERE id = ?";
        jdbc.update(sql, invoice.getInvoice_company_id(), invoice.getInvoice_contact_id(), invoice.getInvoice_number(), invoice.getValue(), invoice.getCurrency().name(), invoice.getType().name(), invoice.getStatus().name(), id);
        return "Successfully updated invoice with id "+id;
    }

    public String deleteInvoice(Integer id){
        String sql = "DELETE FROM invoice WHERE id = ?";
        jdbc.update(sql, id);
        return "Successfully deleted invoice with id "+id;
    }

    public List<Invoice> getAllInvoice(){
        String sql = "SELECT * FROM invoice";
        return jdbc.query(sql, invoiceRowMapper());
    }

    public List<InvoiceDTO> getAllInvoiceDTO(){
        String sql = "SELECT i.invoice_number, i.timestamp, c.name, c.type, con.firstname FROM invoice i LEFT JOIN company c ON c.id = i.invoice_company_id LEFT JOIN contact con ON con.id = i.invoice_contact_id";
        return jdbc.query(sql, invoiceDTORowMapper());
    }

    public Invoice getInvoiceById(Integer id){
        String sql = "SELECT * FROM invoice WHERE id =?";
        return jdbc.queryForObject(sql,new Object[]{id}, invoiceRowMapper());
    }

    public static RowMapper<InvoiceDTO> invoiceDTORowMapper(){
        return (r,i)->{
            InvoiceDTO rowObject = new InvoiceDTO();
            rowObject.setNumber(r.getString("invoice_number"));
            rowObject.setDate(r.getTimestamp("timestamp"));
            rowObject.setCompany(r.getString("name"));
            rowObject.setType(CompanyType.valueOf(r.getString("type")));
            rowObject.setContact(r.getString("firstname"));

            return rowObject;
        };
    }

    public static RowMapper<Invoice> invoiceRowMapper(){
        return (r, i) -> {
            Invoice rowObject = new Invoice();
            rowObject.setId(r.getInt("id"));
            rowObject.setInvoice_company_id(r.getInt("invoice_company_id"));
            rowObject.setInvoice_contact_id(r.getInt("invoice_contact_id"));
            rowObject.setInvoice_number(r.getString("invoice_number"));
            rowObject.setValue(r.getBigDecimal("value"));
            rowObject.setCurrency(InvoiceCurrency.valueOf(r.getString("currency")));
            rowObject.setType(InvoiceType.valueOf(r.getString("type")));
            rowObject.setStatus(InvoiceStatus.valueOf(r.getString("status")));
            rowObject.setTimestamp(r.getTimestamp("timestamp"));
            return rowObject;
        };
    }
}
