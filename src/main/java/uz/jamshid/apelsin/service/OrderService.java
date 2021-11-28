package uz.jamshid.apelsin.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.*;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.payload.CustomerLastOrderDto;
import uz.jamshid.apelsin.payload.NumberOfProductsInYearDto;
import uz.jamshid.apelsin.payload.OrderDto;
import uz.jamshid.apelsin.repository.*;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    DetailRepository detailRepository;
    @Autowired
    ProductRepository productRepository;

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

    public ApiResponse addOrder(OrderDto orderDto) {
        try {
            Order order = new Order();
            order.setDate(new Date());

            Optional<Customer> optionalCustomer = customerRepository.findById(orderDto.getCustomerId());
            if (!optionalCustomer.isPresent())
                return new ApiResponse("FAILED. Customer not found", false);

            order.setCustomer(optionalCustomer.get());
            orderRepository.save(order);

            Detail detail = new Detail();
            detail.setOrder(order);
            detail.setQuantity(orderDto.getQuantity());

            Optional<Product> optionalProduct = productRepository.findById(orderDto.getProductId());
            if (!optionalProduct.isPresent())
                return new ApiResponse("FAILED. Product not found", false);

            detail.setProduct(optionalProduct.get());
            detailRepository.save(detail);

            Invoice invoice = new Invoice();
            invoice.setOrder(order);
            invoice.setIssued(new Date());
            invoice.setDue(new Date());
            invoice.setAmount(detail.getQuantity() * detail.getProduct().getPrice());
            invoiceRepository.save(invoice);

            return new ApiResponse("SUCCESS", true, "invoice_number: " + invoice.getId());
        } catch (Exception exception) {
            return new ApiResponse("FAILED. Exception occurred", false);
        }
    }

    public ApiResponse getOrderById(Integer order_id) {
        try {
            Optional<Order> optionalOrder = orderRepository.findById(order_id);
            if (!optionalOrder.isPresent())
                return new ApiResponse("Order not found", false);

            Detail detail = detailRepository.findByOrderId(order_id);

            return new ApiResponse("Order details and product name", true, optionalOrder.get() + detail.getProduct().getName());
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }
}
