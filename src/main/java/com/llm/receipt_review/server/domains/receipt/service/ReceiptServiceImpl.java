package com.llm.receipt_review.server.domains.receipt.service;

import com.llm.receipt_review.server.constant.exception.CustomException;
import com.llm.receipt_review.server.constant.response.CustomResponseStatus;
import com.llm.receipt_review.server.domains.receipt.dto.ReceiptOcrDto;
import com.llm.receipt_review.server.domains.receipt.dto.ReceiptReqDto;
import com.llm.receipt_review.server.domains.receipt.entity.Receipt;
import com.llm.receipt_review.server.domains.receipt.mapper.ReceiptMapper;
import com.llm.receipt_review.server.domains.receipt.repository.ReceiptRepository;
import com.llm.receipt_review.server.domains.receipt.service.Upstage.UpstageApiService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

    private final UpstageApiService upstageApiService;
    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;

    @Transactional
    public ReceiptOcrDto registReceipt(ReceiptReqDto receiptReqDto, String clientId, MultipartFile receiptPhotoFile) throws IOException {

        ReceiptOcrDto receiptOcrDto = upstageApiService.apiReceiptOcr(receiptPhotoFile);

        boolean receiptValidation = validateReceipt(receiptReqDto, receiptOcrDto);

        Receipt receipt = receiptMapper.toReceipt(receiptOcrDto, receiptReqDto.storeId(), clientId);
        Receipt savedReceipt = null;

        try {
            if (receipt.getStoreName() != null && receiptValidation) {
                savedReceipt = receiptRepository.save(receipt);
                log.info("Receipt saved Successfully");
            } else {
                log.error("Map Struct Error for DTO to Entity : " + receiptOcrDto);
            }
            //Saved Error가 나더라도 영수증 리뷰 기능에는 문제가 없으므로 Error가 아닌 log로 기록만 남기고 정상 작동
        } catch (Exception e) {
            throw new CustomException(CustomResponseStatus.REDUNDANT_RECEIPT);
        }


        return savedReceipt != null ? receiptMapper.toDto(savedReceipt) : receiptOcrDto;

    }


    //검증 로직.
    private boolean validateReceipt(ReceiptReqDto req, ReceiptOcrDto receiptOcrDto) {

        //Req에 사업자번호 데이터 존재하는 경우에만 검증
        if (req.storeRegistrationNumber() != null && !req.storeRegistrationNumber().isEmpty()) {
            log.info("사업자번호 쿼리에 존재");
            if (!isEqualRegNum(receiptOcrDto.storeRegistrationNumber(), req.storeRegistrationNumber())) {
                throw new CustomException(CustomResponseStatus.STORE_MATCH_ERROR);
            }
        }
        if(receiptOcrDto.approvalCode() == null)
            throw new CustomException(CustomResponseStatus.INVALID_RECEIPT);

        return true;
    }
    private boolean isEqualRegNum(String regNumOcr, String regNumData) {
        return regNumOcr.equals(regNumData);
    }


}




