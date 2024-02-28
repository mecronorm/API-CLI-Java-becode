package org.cogip.cogiprestapi.model;

import jakarta.persistence.*;
import org.cogip.cogiprestapi.enums.InvoiceCurrency;
import org.cogip.cogiprestapi.enums.InvoiceStatus;
import org.cogip.cogiprestapi.enums.InvoiceType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int invoice_company_id;

    @Column(nullable = false)
    private int invoice_contact_id;

    @Column(nullable = false)
    private String invoice_number;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "currency")
    private InvoiceCurrency currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "type")
    private InvoiceType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private InvoiceStatus status;

    @Column(name = "timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoice_company_id() {
        return invoice_company_id;
    }

    public void setInvoice_company_id(int invoice_company_id) {
        this.invoice_company_id = invoice_company_id;
    }

    public int getInvoice_contact_id() {
        return invoice_contact_id;
    }

    public void setInvoice_contact_id(int invoice_contact_id) {
        this.invoice_contact_id = invoice_contact_id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public InvoiceCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(InvoiceCurrency currency) {
        this.currency = currency;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public InvoiceType getType() {
        return type;
    }

    public void setType(InvoiceType type) {
        this.type = type;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
