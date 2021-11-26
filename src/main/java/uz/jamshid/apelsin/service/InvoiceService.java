package uz.jamshid.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.Invoice;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.repository.InvoiceRepository;
import uz.jamshid.apelsin.repository.OrderRepository;

import java.util.Set;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    OrderRepository orderRepository;

    public ApiResponse getExpiredInvoices() {
        Set<Invoice> expiredInvoices = invoiceRepository.getExpiredInvoices();
        return new ApiResponse("All expired invoices", true, expiredInvoices);
    }

    public ApiResponse getWrongDateInvoices() {
        Set<Invoice> wrongDateInvoices = invoiceRepository.getWrongDateInvoice();
        return new ApiResponse("All wrong date invoices and orders", true, wrongDateInvoices);
    }
}
