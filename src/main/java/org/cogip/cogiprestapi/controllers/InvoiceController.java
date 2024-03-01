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
  
  @PostMapping ("/add")
  public String createInvoice(@RequestBody Invoice invoice){
    return invoiceService.createInvoice(invoice);
  }
  
  @PutMapping("/edit/{id}")
  public String updateInvoice(@PathVariable("id") String id, @RequestBody Invoice invoice){
    return invoiceService.updateInvoice(Integer.valueOf(id), invoice);
  }
  
  @DeleteMapping("/delete/{id}")
  public String deleteInvoice(@PathVariable("id") String id){
    return invoiceService.deleteInvoice(Integer.valueOf(id));
  }
  
  @GetMapping("/{id}")
  public Invoice getInvoiceById(@PathVariable("id") String id){
    return invoiceService.getInvoiceById(Integer.valueOf(id));
  }
  
  @GetMapping
  public List<Invoice> getAllInvoice(){
    return invoiceService.getAllInvoice();
  }
}