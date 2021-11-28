package uz.jamshid.apelsin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.payload.PaymentDto;
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

    /**
     * 17.	POST /payment
     *
     * @param paymentDto
     * @return ApiResponse
     */
    @PostMapping()
    public HttpEntity<?> makePayment(@RequestBody PaymentDto paymentDto) {
        ApiResponse apiResponse = paymentService.makePayment(paymentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    /**
     * 18.	GET /payment/details?id={payment_details_id}
     *
     * @param payment_details_id
     * @return ApiResponse
     */
    @GetMapping("/details/id={payment_details_id}")
    public HttpEntity<?> getPaymentById(@PathVariable Integer payment_details_id) {
        ApiResponse apiResponse = paymentService.getPaymentById(payment_details_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
