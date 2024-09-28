package com.llm.receipt_review.server.domains.receipt.service;

import com.llm.receipt_review.server.constant.exception.CustomException;
import com.llm.receipt_review.server.constant.response.CustomResponseStatus;
import com.llm.receipt_review.server.domains.receipt.mapper.ReceiptMapper;
import com.llm.receipt_review.server.domains.receipt.dto.ReceiptOcrDto;
import com.llm.receipt_review.server.domains.receipt.dto.ReceiptReqDto;
import com.llm.receipt_review.server.domains.receipt.entity.Receipt;
import com.llm.receipt_review.server.domains.receipt.repository.ReceiptRepository;
import com.llm.receipt_review.server.domains.receipt.service.Upstage.UpstageApiService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static com.llm.receipt_review.server.constant.util.JascksonUtil.objectMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

    private final UpstageApiService upstageApiService;
    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;

    @Transactional
    public ReceiptOcrDto registReceipt(ReceiptReqDto receiptReqDto, MultipartFile receiptPhotoFile) throws IOException {

        Map<String, Object> receiptMap = upstageApiService.apiReceiptOcr(receiptPhotoFile);
        ReceiptOcrDto receiptOcrDto = objectMapper.convertValue(receiptMap, ReceiptOcrDto.class);
        log.info("성공적 Mapping: " + receiptOcrDto.toString());

        boolean receiptValidation = validateReceipt(receiptReqDto, receiptOcrDto);
        Receipt receipt = receiptMapper.toReceipt(receiptOcrDto, receiptReqDto.storeId());

        if(receipt.getStoreName() != null){
            log.info("MapStruct: " + receipt.getStoreName());

            if(receiptValidation){
                Receipt savedReceipt= receiptRepository.save(receipt);
                log.info("Receipt saved Successfully");
                log.info(savedReceipt.getStoreName());
            }
        }
        else{
            log.error("Map Struct Error for DTO to Entity : "+ receiptOcrDto);
        }

        return receiptOcrDto;
    }




    //검증 로직.
    private boolean validateReceipt(ReceiptReqDto req, ReceiptOcrDto receiptOcrDto){

        if (!validApprovalCode(receiptOcrDto.approvalCode())) {
            throw new CustomException(CustomResponseStatus.REDUNDANT_RECEIPT);
        }

        //Req에 사업자번호 데이터 존재하는 경우에만 검증
        if(req.storeRegistrationNumber() != null && !req.storeRegistrationNumber().isEmpty()){
            log.info("사업자번호 쿼리에 존재");
            if(!isEqualRegNum(receiptOcrDto.storeRegistrationNumber(), req.storeRegistrationNumber())){
                throw new CustomException(CustomResponseStatus.STORE_MATCH_ERROR);
            }
        }

        return true;
    }

    private boolean validApprovalCode(String approvalCode){

        return !receiptRepository.existsByApprovalCode(approvalCode);
        //동일 승인번호가 없어야 Valid 하다.

    }
    private boolean isEqualRegNum(String regNumOcr, String regNumData){

        return regNumOcr.equals(regNumData);
    }



}




