package uz.jamshid.apelsin.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.Customer;
import uz.jamshid.apelsin.entity.Detail;
import uz.jamshid.apelsin.entity.Invoice;
import uz.jamshid.apelsin.entity.Order;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.payload.CustomerLastOrderDto;
import uz.jamshid.apelsin.payload.NumberOfProductsInYearDto;
import uz.jamshid.apelsin.payload.OrderDto;
import uz.jamshid.apelsin.repository.CustomerRepository;
import uz.jamshid.apelsin.repository.DetailRepository;
import uz.jamshid.apelsin.repository.InvoiceRepository;
import uz.jamshid.apelsin.repository.OrderRepository;

import javax.persistence.Tuple;
import java.math.BigInteger;
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
            order.setDate(orderDto.getDate());

            Optional<Customer> optionalCustomer = customerRepository.findById(orderDto.getCustomerId());
            if (!optionalCustomer.isPresent())
                return new ApiResponse("FAILED. Customer not found", false);

            order.setCustomer(optionalCustomer.get());
            orderRepository.save(order);

            Invoice invoice = new Invoice();
            invoice.setOrder(order);
            invoice.setIssued(orderDto.getDate());
            invoice.setDue(orderDto.getDate());
            invoice.setAmount((double) Math.round(Math.random() * 100));
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
