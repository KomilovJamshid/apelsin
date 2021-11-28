package uz.jamshid.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.Invoice;
import uz.jamshid.apelsin.entity.Payment;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.payload.OverpaidInvoiceDto;
import uz.jamshid.apelsin.payload.PaymentDto;
import uz.jamshid.apelsin.repository.InvoiceRepository;
import uz.jamshid.apelsin.repository.PaymentRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

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

    public ApiResponse makePayment(PaymentDto paymentDto) {
        try {
            Payment payment = new Payment();
            payment.setAmount((double) Math.round(Math.random() * 100));
            payment.setTime(Timestamp.valueOf(LocalDateTime.now()));

            Optional<Invoice> optionalInvoice = invoiceRepository.findById(paymentDto.getInvoiceId());
            if (!optionalInvoice.isPresent())
                return new ApiResponse("FAILED. Invoice not found", false);

            payment.setInvoice(optionalInvoice.get());
            paymentRepository.save(payment);
            return new ApiResponse("SUCCESS", true, payment);
        } catch (Exception exception) {
            return new ApiResponse("FAILED. Exception occurred", false);
        }
    }

    public ApiResponse getPaymentById(Integer paymentId) {
        try {
            Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
            return optionalPayment.map(payment -> new ApiResponse("SUCCESS", true, payment)).orElseGet(() -> new ApiResponse("FAILED. Payment not found", false));
        } catch (Exception exception) {
            return new ApiResponse("FAILED. Exception occurred", false);
        }
    }
}
