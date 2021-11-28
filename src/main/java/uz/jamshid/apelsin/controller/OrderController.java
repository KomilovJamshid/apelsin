package uz.jamshid.apelsin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.payload.OrderDto;
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
     * 15.	POST /order
     *
     * @param orderDto
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<?> addOrder(@RequestBody OrderDto orderDto) {
        ApiResponse apiResponse = orderService.addOrder(orderDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    /**
     * 16.	GET order/details?order_id={order_id}
     *
     * @param order_id
     * @return ApiResponse
     */
    @GetMapping("/details/order_id={order_id}")
    public HttpEntity<?> getOrderById(@PathVariable Integer order_id) {
        ApiResponse apiResponse = orderService.getOrderById(order_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
