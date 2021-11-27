package uz.jamshid.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.Customer;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.payload.CustomerLastOrderDto;
import uz.jamshid.apelsin.repository.CustomerRepository;

import java.util.Set;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public ApiResponse getCustomersWithoutOrders() {
        try {
            Set<Customer> customerWithoutOrders = customerRepository.getCustomerWithoutOrders();
            return new ApiResponse("Customers without orders", true, customerWithoutOrders);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }
}
