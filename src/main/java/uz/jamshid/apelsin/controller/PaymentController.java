package uz.jamshid.apelsin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    /**
     * 6.	Invoices that have been overpaid. Observe that there may be more than one payment
     * referring to the same invoice. Return the invoice number and the amount that should be
     * reimbursed.
     *
     * @return ApiResponse
     */
    @GetMapping("/overpaid_invoices")
    public HttpEntity<?> getOverpaidInvoices() {
        ApiResponse apiResponse = paymentService.getOverpaidInvoices();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
