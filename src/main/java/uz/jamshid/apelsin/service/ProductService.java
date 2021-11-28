package uz.jamshid.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.Product;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ApiResponse getProductList() {
        try {
            List<Product> productList = productRepository.findAll();
            return new ApiResponse("Product list", true, productList);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }

    public ApiResponse getProductById(Integer product_id) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(product_id);
            if (!optionalProduct.isPresent())
                return new ApiResponse("Product not found", false);
            return new ApiResponse("Product with id = " + optionalProduct.get().getId(), true, optionalProduct);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }
}
