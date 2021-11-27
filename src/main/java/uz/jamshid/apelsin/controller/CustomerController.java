package uz.jamshid.apelsin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    /**
     * 4.   Customers who have not placed any orders in 2016. Return all attributes.
     *
     * @return ApiResponse
     */
    @GetMapping("/customers_without_orders")
    public HttpEntity<?> getCustomersWithoutOrders() {
        ApiResponse apiResponse = customerService.getCustomersWithoutOrders();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
