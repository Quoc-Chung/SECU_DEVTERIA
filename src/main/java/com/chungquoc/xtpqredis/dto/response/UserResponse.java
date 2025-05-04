package com.chungquoc.xtpqredis.dto.response;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
  String id;
  String username;
  String password;
  String firstName;
  String lastName;
  String soDienThoai;
  LocalDate ngaySinh;
}
