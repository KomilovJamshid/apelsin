package uz.jamshid.apelsin.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.Invoice;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.payload.WrongDateInvoiceDto;
import uz.jamshid.apelsin.repository.InvoiceRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ModelMapper modelMapper;

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
            Set<WrongDateInvoiceDto> wrongDateInvoiceDtoSet = invoiceRepository.getWrongDateInvoice()
                    .stream()
                    .map(this::convertWrongDateInvoiceDto)
                    .collect(Collectors.toSet());
            return new ApiResponse("All wrong date invoices and orders", true, wrongDateInvoiceDtoSet);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }

    private WrongDateInvoiceDto convertWrongDateInvoiceDto(Invoice invoice) {
        WrongDateInvoiceDto wrongDateInvoiceDto = new WrongDateInvoiceDto();
        wrongDateInvoiceDto.setInvoiceId(invoice.getId());
        wrongDateInvoiceDto.setIssued(invoice.getIssued());
        wrongDateInvoiceDto.setOrderDate(invoice.getOrder().getDate());
        wrongDateInvoiceDto.setOrderId(invoice.getOrder().getId());
        return wrongDateInvoiceDto;
    }
}
