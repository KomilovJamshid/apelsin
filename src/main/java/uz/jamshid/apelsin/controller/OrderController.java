package uz.jamshid.apelsin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    /**
     * 3.   Orders that do not have a detail and were placed before 6 September 2016. Return all
     * attributes.
     *
     * @return ApiResponse
     */
    @GetMapping("/orders_without_details")
    public HttpEntity<?> getOrdersWithoutDetails() {
        ApiResponse apiResponse = orderService.getOrdersWithoutDetails();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
