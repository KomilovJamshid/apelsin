package uz.jamshid.apelsin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    /**
     * 13.	GET /product/list
     *
     * @return ApiResponse
     */
    @GetMapping("/list")
    public HttpEntity<?> getProductList() {
        ApiResponse apiResponse = productService.getProductList();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * 14.	GET  /product/details?product_id={product_id}
     *
     * @param product_id
     * @return ApiResponse
     */
    @GetMapping("/details/product_id={product_id}")
    public HttpEntity<?> getProductById(@PathVariable Integer product_id) {
        ApiResponse apiResponse = productService.getProductById(product_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
