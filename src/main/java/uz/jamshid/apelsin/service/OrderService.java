package uz.jamshid.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.Order;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.repository.OrderRepository;

import java.util.Set;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public ApiResponse getOrdersWithoutDetails() {
        try {
            Set<Order> ordersWithoutDetail = orderRepository.getOrdersWithoutDetail();
            return new ApiResponse("All orders without detail", true, ordersWithoutDetail);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }

    public ApiResponse getNumberOfProductsInYear() {
        try {
            Set<Order> numberOfProductsInYear = orderRepository.getNumberOfProductsInYear();
            return new ApiResponse("Number of products in year", true, numberOfProductsInYear);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }

    public ApiResponse getOrdersWithoutInvoices() {
        try {
            Set<Order> ordersWithoutInvoices = orderRepository.getOrdersWithoutInvoices();
            return new ApiResponse("Orders without invoices", true, ordersWithoutInvoices);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }
}
