package uz.jamshid.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.Invoice;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.repository.InvoiceRepository;

import java.util.Set;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;

    public ApiResponse getExpiredInvoices() {
        try {
            Set<Invoice> expiredInvoices = invoiceRepository.getExpiredInvoices();
            return new ApiResponse("All expired invoices", true, expiredInvoices);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }

    public ApiResponse getWrongDateInvoices() {
        try {
            Set<Invoice> wrongDateInvoices = invoiceRepository.getWrongDateInvoice();
            return new ApiResponse("All wrong date invoices and orders", true, wrongDateInvoices);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }

    public ApiResponse getOverpaidInvoices() {
        try {
            Set<Invoice> overpaidInvoices = invoiceRepository.getOverpaidInvoices();
            return new ApiResponse("Overpaid invoices", true, overpaidInvoices);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }
}
