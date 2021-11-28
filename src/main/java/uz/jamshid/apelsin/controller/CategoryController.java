package uz.jamshid.apelsin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 11.	GET /category/list
     *
     * @return ApiResponse
     */
    @GetMapping("/list")
    public HttpEntity<?> getCategoryList() {
        ApiResponse apiResponse = categoryService.getCategoryList();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * 12.	GET /category?product_id={product_id}
     *
     * @param product_id
     * @return ApiResponse
     */
    @GetMapping("/product_id={product_id}")
    public HttpEntity<?> getCategoryByProductId(@PathVariable Integer product_id) {
        ApiResponse apiResponse = categoryService.getCategoryByProductId(product_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
