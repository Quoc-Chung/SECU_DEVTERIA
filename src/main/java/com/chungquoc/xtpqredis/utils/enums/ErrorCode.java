package com.chungquoc.xtpqredis.utils.enums;

public enum ErrorCode {
  USER_EXISTS(123,"user đã tồn tại rồi nha đừng tạo nữa- trùng lặp username rồi. "),

  USER_NOT_EXISTS(345,"user có tồn tại đâu mà đòi lấy"),

  UNCATEGORIZE_EXCEPTION(567,"Ban dang gap loi vo van xung quanh ");
  private int code;
  private String message;
  ErrorCode(int code, String message) {
    this.code = code;
    this.message = message;
  }
  public int getCode() {
    return code;
  }
  public void setCode(int code) {
    this.code = code;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
      this.message = message;
  }





}
