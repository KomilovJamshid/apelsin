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
}
