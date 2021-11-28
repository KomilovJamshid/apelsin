package uz.jamshid.apelsin.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.Customer;
import uz.jamshid.apelsin.entity.Order;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.payload.CustomerLastOrderDto;
import uz.jamshid.apelsin.payload.NumberOfProductsInYearDto;
import uz.jamshid.apelsin.repository.OrderRepository;

import javax.persistence.Tuple;
import java.math.BigInteger;
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
            Set<Tuple> productDto = orderRepository.getNumberOfProductsInYear();
            Set<NumberOfProductsInYearDto> productsInYearDtoSet = productDto.stream()
                    .map(t -> new NumberOfProductsInYearDto(
                            t.get(0, BigInteger.class),
                            t.get(1, String.class)
                    ))
                    .collect(Collectors.toSet());
            return new ApiResponse("Number of products in year", true, productsInYearDtoSet);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }
}
