package uz.jamshid.apelsin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.service.InvoiceService;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    /**
     * 1.	Invoices issued after their due date. Return all attributes.
     *
     * @return ApiResponse
     */
    @GetMapping("/expired_invoices")
    public HttpEntity<?> getExpiredInvoices() {
        ApiResponse apiResponse = invoiceService.getExpiredInvoices();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * 2.   Invoices that were issued before the date in which the order they refer to was placed.
     * Return the ID of the invoice, the date it was issued, the ID of the order associated with it
     * and the date the order was placed.
     *
     * @return ApiResponse
     */
    @GetMapping("/wrong_date_invoices")
    public HttpEntity<?> getWrongDateInvoices() {
        ApiResponse apiResponse = invoiceService.getWrongDateInvoices();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
