package com.chungquoc.xtpqredis.dto.response;

import org.springframework.http.ResponseEntity;

public class ResponseFactory {

  public static <T> ResponseEntity<GeneralResponse<T>> success() {
    GeneralResponse<T> responseObject = new GeneralResponse<>();
    responseObject.setStatus(ResponseStatus.SUCCESS_STATUS);
    return ResponseEntity.ok(responseObject);
  }

  public static <T> ResponseEntity<GeneralResponse<T>> success(T data){
    GeneralResponse<T>  response = new GeneralResponse<>();
    response.setStatus(ResponseStatus.SUCCESS_STATUS);
    response.setData(data);
    response.setExtraData(null);
    return ResponseEntity.ok(response);
  }

}
