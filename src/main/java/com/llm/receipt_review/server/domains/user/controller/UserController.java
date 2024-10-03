package com.llm.receipt_review.server.domains.user.controller;



import com.llm.receipt_review.server.constant.response.ApiResponse;
import com.llm.receipt_review.server.domains.user.dto.UserReqDto;
import com.llm.receipt_review.server.domains.user.dto.UserRespDto;
import com.llm.receipt_review.server.domains.user.entity.User;
import com.llm.receipt_review.server.domains.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;



    @PostMapping("/admin/signup")
    public ResponseEntity<ApiResponse<UserRespDto>> registerUser(@RequestBody @Valid UserReqDto userReqDto){

        UserRespDto user = userService.registUser(userReqDto);

        return ApiResponse.createSuccessWithOk(user);
    }

}
