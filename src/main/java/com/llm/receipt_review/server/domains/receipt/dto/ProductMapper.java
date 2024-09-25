//package com.llm.receipt_review.server.domains.receipt.dto;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Mapper
//public interface ProductMapper {
//    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
//
//    // productList를 List<ProductOcrDto>로 변환하는 매핑 메서드
//    ProductOcrDto  toProductOcrDto(Map<String, Object> product);
//
//    // productList 변환 메소드
//    default List<ProductOcrDto> mapProductList(Object productList) {
//        if (productList instanceof List) {
//            List<Map<String, Object>> productMaps = (List<Map<String, Object>>) productList;
//            return productMaps.stream()
//                    .map(this::toProductOcrDto)
//                    .collect(Collectors.toList());
//        }
//        return new ArrayList<>();
//    }
//
//    default String map(Object value) {
//        return value != null ? value.toString() : null;
//    }
//    default String replaceKeyToFieldName(String key) {
//        return key.replace("group_0/menu.", "");
//    }
//}
