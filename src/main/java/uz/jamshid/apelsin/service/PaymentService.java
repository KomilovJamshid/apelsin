package uz.jamshid.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.Payment;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.payload.OverpaidInvoiceDto;
import uz.jamshid.apelsin.repository.PaymentRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public ApiResponse getOverpaidInvoices() {
        try {
            Set<OverpaidInvoiceDto> overpaidInvoiceDtoSet = paymentRepository.getOverpaidInvoices()
                    .stream()
                    .map(this::convertOverpaidInvoiceDto)
                    .collect(Collectors.toSet());
            return new ApiResponse("Overpaid invoices", true, overpaidInvoiceDtoSet);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }

    private OverpaidInvoiceDto convertOverpaidInvoiceDto(Payment payment) {
        OverpaidInvoiceDto overpaidInvoiceDto = new OverpaidInvoiceDto();
        overpaidInvoiceDto.setInvoiceId(payment.getInvoice().getId());
        overpaidInvoiceDto.setReimburseAmount(payment.getAmount() - payment.getInvoice().getAmount());
        return overpaidInvoiceDto;
    }
}
