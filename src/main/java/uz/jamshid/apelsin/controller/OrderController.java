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

    /**
     * 5.	ID and name of customers and the date of their last order. For customers who did not
     * place any orders, no rows must be returned. For each customer who placed more than
     * one order on the date of their most recent order, only one row must be returned.
     *
     * @return ApiResponse
     */
    @GetMapping("/customers_last_order")
    public HttpEntity<?> getCustomersLastOrders() {
        ApiResponse apiResponse = orderService.getCustomersLastOrders();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * 9.	Total number of orders placed in 2016 by customers of each country. If all customers
     * from a specific country did not place any orders in 2016, the country will not appear in
     * the output.
     *
     * @return ApiResponse
     */
    @GetMapping("/number_of_products_in_year")
    public HttpEntity<?> getNumberOfProductsInYear() {
        ApiResponse apiResponse = orderService.getNumberOfProductsInYear();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * 10.	For each order without invoice, list its ID, the date it was placed and the total price of the
     * products in its detail, taking into account the quantity of each ordered product and its unit
     * price. Orders without detail must not be included in the answers.
     *
     * @return ApiResponse
     */
    @GetMapping("/orders_without_invoices")
    public HttpEntity<?> getOrdersWithoutInvoices() {
        ApiResponse apiResponse = orderService.getOrdersWithoutInvoices();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
