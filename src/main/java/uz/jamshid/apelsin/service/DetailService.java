package uz.jamshid.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jamshid.apelsin.entity.Detail;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.payload.BulkProductsDto;
import uz.jamshid.apelsin.payload.HighDemandProductDto;
import uz.jamshid.apelsin.payload.OrdersWithoutInvoiceDto;
import uz.jamshid.apelsin.repository.DetailRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DetailService {
    @Autowired
    DetailRepository detailRepository;

    public ApiResponse getHighDemandProducts() {
        try {
            Set<HighDemandProductDto> highDemandProductDtoSet = detailRepository.getHighDemandProduct()
                    .stream()
                    .map(this::convertHighDemandProductDto)
                    .collect(Collectors.toSet());
            return new ApiResponse("High demand products", true, highDemandProductDtoSet);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }

    private HighDemandProductDto convertHighDemandProductDto(Detail detail) {
        HighDemandProductDto highDemandProductDto = new HighDemandProductDto();
        highDemandProductDto.setProductId(detail.getProduct().getId());
        highDemandProductDto.setQuantity(detail.getQuantity());
        return highDemandProductDto;
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
            Set<OrdersWithoutInvoiceDto> ordersWithoutInvoiceDtoSet = detailRepository.getOrdersWithoutInvoices()
                    .stream()
                    .map(this::convertOrdersWithoutInvoiceDto)
                    .collect(Collectors.toSet());
            return new ApiResponse("Orders without invoices", true, ordersWithoutInvoiceDtoSet);
        } catch (Exception exception) {
            return new ApiResponse("Exception occurred", false);
        }
    }

    private OrdersWithoutInvoiceDto convertOrdersWithoutInvoiceDto(Detail detail) {
        OrdersWithoutInvoiceDto ordersWithoutInvoiceDto = new OrdersWithoutInvoiceDto();
        ordersWithoutInvoiceDto.setOrderId(detail.getOrder().getId());
        ordersWithoutInvoiceDto.setOrderDate(detail.getOrder().getDate());
        ordersWithoutInvoiceDto.setTotalPrice(detail.getQuantity() * detail.getProduct().getPrice());
        return ordersWithoutInvoiceDto;
    }
}
