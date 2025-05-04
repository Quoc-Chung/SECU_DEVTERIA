package com.chungquoc.xtpqredis.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseStatus {
     /*- Bên trong một lớp, ta có thể khai báo các cái biến toàn cục -*/
     private String code;
     private String message;
     private String label;

  public static final String SUCCESS_CODE = "200";
  public static final String PERMISSION_DENIED_CODE = "403";
  public static final String GENERAL_ERROR_CODE = "40000";

  public static final String SUCCESS_MESSAGE = "Thành công";
  public static final String PERMISSION_DENIED_MESSAGES = "Không có quyền thực hiện chức năng này";
  public static final String GENERAL_ERROR_MESSAGE = "Có lỗi xảy ra";

  public static final String SUCCESS_LABEL = "Success";
  public static final String PERMISSION_DENIED_LABEL = "Permission Denied";


  /*- tự tạo các đối tượng tĩnh nằm bên trong class -*/
  public static final ResponseStatus SUCCESS_STATUS = new ResponseStatus(SUCCESS_CODE, SUCCESS_MESSAGE, SUCCESS_LABEL);
  public static final ResponseStatus PERMISSION_DENIED_STATUS = new ResponseStatus(PERMISSION_DENIED_CODE, PERMISSION_DENIED_MESSAGES, PERMISSION_DENIED_LABEL);
}
