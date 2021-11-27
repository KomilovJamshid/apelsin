package uz.jamshid.apelsin.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.Customer;
import uz.jamshid.apelsin.entity.Order;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.payload.CustomerLastOrderDto;
import uz.jamshid.apelsin.repository.OrderRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ModelMapper modelMapper;

    public ApiResponse getOrdersWithoutDetails() {
        try {
            Set<Order> ordersWithoutDetail = orderRepository.getOrdersWithoutDetail();
            return new ApiResponse("All orders without detail", true, ordersWithoutDetail);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }

    public ApiResponse getCustomersLastOrders() {
        try {
            Set<CustomerLastOrderDto> customerLastOrderDtoSet = orderRepository.getCustomersLastOrders()
                    .stream()
                    .map(this::convertCustomerLastOrderDto)
                    .collect(Collectors.toSet());
            return new ApiResponse("Customers last orders", true, customerLastOrderDtoSet);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }

    private CustomerLastOrderDto convertCustomerLastOrderDto(Order order) {
        CustomerLastOrderDto customerLastOrderDto = new CustomerLastOrderDto();
        customerLastOrderDto.setCustomerId(order.getCustomer().getId());
        customerLastOrderDto.setName(order.getCustomer().getName());
        customerLastOrderDto.setLastOrderDate(order.getDate());
        return customerLastOrderDto;
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
