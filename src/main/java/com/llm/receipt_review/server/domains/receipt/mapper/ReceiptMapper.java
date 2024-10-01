package com.llm.receipt_review.server.domains.receipt.mapper;

import com.llm.receipt_review.server.domains.receipt.dto.ProductOcrDto;
import com.llm.receipt_review.server.domains.receipt.dto.ReceiptOcrDto;
import com.llm.receipt_review.server.domains.receipt.entity.Product;
import com.llm.receipt_review.server.domains.receipt.entity.Receipt;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceiptMapper {
    ReceiptMapper INSTANCE = Mappers.getMapper(ReceiptMapper.class);

    Receipt toReceipt(ReceiptOcrDto receiptOcrDto, String storeId, String clientId);

    Product toProduct(ProductOcrDto productOcrDto);
    // List<ProductOcrDto> -> List<Product>로 변환하는 메서드 자동 생성
    List<Product> toProductList(List<ProductOcrDto> productOcrDtoList);

    ReceiptOcrDto toDto(Receipt receipt);

}
