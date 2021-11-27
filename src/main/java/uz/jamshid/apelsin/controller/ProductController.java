package uz.jamshid.apelsin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 7.	Products that were ordered more than 10 times in total, by taking into account the
     * quantities in which they appear in the order details. Return the product code and the
     * total number of times it was ordered.
     *
     * @return ApiResponse
     */
    @GetMapping("/high_demand_product")
    public HttpEntity<?> getHighDemandProducts() {
        ApiResponse apiResponse = productService.getHighDemandProducts();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * 8.	Products that are usually ordered in bulk: whenever one of these products is ordered, it
     * is ordered in a quantity that on average is equal to or greater than 8. For each such
     * product, return product code and price.
     *
     * @return ApiResponse
     */
    @GetMapping("/bulk_products")
    public HttpEntity<?> getBulkProducts() {
        ApiResponse apiResponse = productService.getBulkProducts();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
