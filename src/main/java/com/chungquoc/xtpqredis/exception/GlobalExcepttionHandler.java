package com.chungquoc.xtpqredis.exception;

import com.chungquoc.xtpqredis.dto.response.ApiResponse;
import com.chungquoc.xtpqredis.dto.response.AppException;
import com.chungquoc.xtpqredis.utils.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExcepttionHandler {

  @ExceptionHandler(value= MethodArgumentNotValidException.class)

  ResponseEntity<ApiResponse<?>> handlingValidationException(MethodArgumentNotValidException e) {
    ApiResponse<?> apiResponse = new ApiResponse<>();
    apiResponse.setCode(HttpStatus.BAD_REQUEST.value());
    apiResponse.setMessage(e.getFieldError().getDefaultMessage());
    apiResponse.setData(null);
    return ResponseEntity.badRequest().body(apiResponse);
  }

  /*- code , messagge , data -*/
  @ExceptionHandler(AppException.class)
  public ResponseEntity<ApiResponse<?>> handleAppException(AppException e) {
    ErrorCode errorCode = e.getErrorCode();

    ApiResponse<?> response = new ApiResponse<>();
    response.setCode(errorCode.getCode());
    response.setMessage(errorCode.getMessage());
    response.setData(null);

    return ResponseEntity.ok(response);
  }


}
