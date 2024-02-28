package org.cogip.cogiprestapi.services;

import org.cogip.cogiprestapi.model.Invoice;
import org.cogip.cogiprestapi.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository){
        this.invoiceRepository = invoiceRepository;
    }

    public String createInvoice(Invoice invoice){
        return invoiceRepository.createInvoice(invoice);
    }

    public String updateInvoice(Integer id, Invoice invoice){
        return invoiceRepository.updateInvoice(id, invoice);
    }

    public String deleteInvoice(Integer id){
        return invoiceRepository.deleteInvoice(id);
    }

    public List<Invoice> getAllInvoice(){
        return invoiceRepository.getAllInvoice();
    }

    public Invoice getInvoiceById(Integer id){
        return invoiceRepository.getInvoiceById(id);
    }


    
}
