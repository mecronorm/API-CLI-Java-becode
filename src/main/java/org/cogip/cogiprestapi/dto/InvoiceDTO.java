package org.cogip.cogiprestapi.dto;

import org.cogip.cogiprestapi.enums.CompanyType;

import java.sql.Timestamp;

public class InvoiceDTO {
    private String number;
    private Timestamp date;
    private String company;
    private CompanyType type;
    private String contact;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public CompanyType getType() {
        return type;
    }

    public void setType(CompanyType type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


}
