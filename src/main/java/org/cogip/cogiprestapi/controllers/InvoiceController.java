package org.cogip.cogiprestapi.controllers;

import org.cogip.cogiprestapi.model.Invoice;
import org.cogip.cogiprestapi.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
  @Autowired
  private InvoiceService invoiceService;
  
  @PostMapping
  public String createInvoice(@RequestBody Invoice invoice){
    return invoiceService.createInvoice(invoice);
  }
  
  @PutMapping("/{id}")
  public String updateInvoice(@PathVariable("id") String id, @RequestBody Invoice invoice){
    return invoiceService.updateInvoice(id, invoice);
  }
  
  @DeleteMapping("/{id}")
  public String deleteInvoice(@PathVariable("id") String id){
    return invoiceService.deleteInvoice(id);
  }
  
  @GetMapping("/{id}")
  public Invoice getInvoiceById(@PathVariable("id") String id){
    return invoiceService.getInvoiceById(id);
  }
  
  @GetMapping
  public List<Invoice> getAllInvoice(){
    return invoiceService.getAllInvoice();
  }
}