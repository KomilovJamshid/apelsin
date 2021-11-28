package uz.jamshid.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.Detail;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.payload.BulkProductsDto;
import uz.jamshid.apelsin.payload.HighDemandProductDto;
import uz.jamshid.apelsin.payload.OrdersWithoutInvoiceDto;
import uz.jamshid.apelsin.repository.DetailRepository;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DetailService {
    @Autowired
    DetailRepository detailRepository;

    public ApiResponse getHighDemandProducts() {
        try {
            Set<Tuple> highDemandProduct = detailRepository.getHighDemandProduct();
            Set<HighDemandProductDto> highDemandProductDtoSet = highDemandProduct.stream()
                    .map(t -> new HighDemandProductDto(
                            t.get(0, Integer.class),
                            t.get(1, BigInteger.class)
                    )).collect(Collectors.toSet());
            return new ApiResponse("High demand products", true, highDemandProductDtoSet);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }

    public ApiResponse getBulkProducts() {
        try {
            Set<BulkProductsDto> bulkProductsDtoSet = detailRepository.getBulkProducts()
                    .stream()
                    .map(this::convertBulkProductsDto)
                    .collect(Collectors.toSet());
            return new ApiResponse("Bulk products", true, bulkProductsDtoSet);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }

    private BulkProductsDto convertBulkProductsDto(Detail detail) {
        BulkProductsDto bulkProductsDto = new BulkProductsDto();
        bulkProductsDto.setProductId(detail.getProduct().getId());
        bulkProductsDto.setPrice(detail.getProduct().getPrice());
        return bulkProductsDto;
    }

    public ApiResponse getOrdersWithoutInvoices() {
        try {
            Set<Tuple> ordersWithoutInvoices = detailRepository.getOrdersWithoutInvoices();
            Set<OrdersWithoutInvoiceDto> ordersWithoutInvoiceDtoSet = ordersWithoutInvoices.stream()
                    .map(t -> new OrdersWithoutInvoiceDto(
                            t.get(0, Integer.class),
                            t.get(1, Date.class),
                            t.get(2, Double.class)
                    )).collect(Collectors.toSet());
            return new ApiResponse("Orders without invoice", true, ordersWithoutInvoiceDtoSet);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }
}
