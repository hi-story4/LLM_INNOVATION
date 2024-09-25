//package com.llm.receipt_review.server.domains.receipt.dto;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//import java.util.Map;
//
//@Mapper(uses = ProductMapper.class)
//public interface ReceiptMapper {
//    ReceiptMapper INSTANCE = Mappers.getMapper(ReceiptMapper.class);
//
//    ReceiptOcrDto toReceiptOcrDto(Map<String, Object> receipt);
//    default String map(Object value) {
//        return value != null ? value.toString(): null;
//    }
//
//    default String replaceKeyToFieldName(String key) {
//        return key.replace("store.", "")
//                .replace("transaction.", "")
//                .replace("total.", "");
//        }
//}
