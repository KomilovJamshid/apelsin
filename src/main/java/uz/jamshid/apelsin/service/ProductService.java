package uz.jamshid.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.Product;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.repository.ProductRepository;

import java.util.Set;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ApiResponse getHighDemandProducts() {
        try {
            Set<Product> highDemandProduct = productRepository.getHighDemandProduct();
            return new ApiResponse("High demand products", true, highDemandProduct);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }
}
