package uz.jamshid.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.Category;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.repository.CategoryRepository;
import uz.jamshid.apelsin.repository.ProductRepository;

import java.util.List;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    public ApiResponse getCategoryList() {
        try {
            List<Category> categoryList = categoryRepository.findAll();
            return new ApiResponse("List of categories", true, categoryList);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }

    public ApiResponse getCategoryByProductId(Integer product_id) {
        try {
            Set<Category> categoryByProductId = categoryRepository.getCategoryByProductId(product_id);
            return new ApiResponse("List of categories", true, categoryByProductId);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }
}
