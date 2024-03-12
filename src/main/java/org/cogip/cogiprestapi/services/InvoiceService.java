package org.cogip.cogiprestapi.services;

import org.cogip.cogiprestapi.Exeptions.*;
import org.cogip.cogiprestapi.model.Invoice;
import org.cogip.cogiprestapi.repositories.InvoiceRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository){
        this.invoiceRepository = invoiceRepository;
    }

    public String createInvoice(Invoice invoice){
        try {
            return invoiceRepository.createInvoice(invoice);
        }catch (NullPointerException e){
            throw new MissingParametersException(invoiceNullError(invoice,newInvoiceNullError(invoice)));
        }catch (DataIntegrityViolationException e){
            throw new DuplicateValueException(invoice.getInvoice_number()+" is a duplicate, only give in an original vat number");
        }
    }

    public String updateInvoice(String id, Invoice invoice){
        try {
            return invoiceRepository.updateInvoice(Integer.valueOf(id), invoice);
        }catch (NullPointerException e){
            String error = "|No input: ";
            throw new MissingParametersException(invoiceNullError(invoice,error));
        }catch (DataIntegrityViolationException e){
            throw new DuplicateValueException(invoice.getInvoice_number()+" is a duplicate, only give in an original vat number");
        }
    }

    public String deleteInvoice(String id){
        getInvoiceById(id);
        return invoiceRepository.deleteInvoice(Integer.valueOf(id));
    }

    public List<Invoice> getAllInvoice(){
        if (invoiceRepository.getAllInvoice().isEmpty()) throw new ResultSetEmptyException("No data in the database");
        return invoiceRepository.getAllInvoice();
    }

    public Invoice getInvoiceById(String id){
        try {
            return invoiceRepository.getInvoiceById(Integer.valueOf(id));
        }catch (EmptyResultDataAccessException e){
            throw new IdNotFoundException("Id number "+id+" not found");
        }catch (NumberFormatException e){
            throw new InvalidInputException("Input is not a number");
        }
    }

    private String newInvoiceNullError(Invoice invoice){
        String error = "|No input: ";
        if (invoice.getInvoice_company_id()==0){
            error = error+"invoice_company_id cannot be null | ";
        }
        if (invoice.getInvoice_contact_id()==0){
            error = error+"invoice_contact_id cannot be null | ";
        }
        return error;
    }

    private String invoiceNullError(Invoice invoice, String error){
        if (invoice.getInvoice_number()==null){
            error = error+"invoice_number cannot be null | ";
        }
        if (invoice.getValue()==null){
            error=error+"value cannot be null | ";
        }
        if (invoice.getCurrency()==null){
            error=error+"currency cannot be null | ";
        }
        if (invoice.getType()==null){
            error=error+"type cannot be null | ";
        }
        if (invoice.getStatus()==null){
            error=error+"status cannot be null | ";
        }
        return error;
    }
}
