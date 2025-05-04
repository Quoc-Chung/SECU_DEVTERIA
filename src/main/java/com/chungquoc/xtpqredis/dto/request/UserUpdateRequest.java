package com.chungquoc.xtpqredis.dto.request;


import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
  String password;
  String firstName;
  String lastName;
  LocalDate dob;
  String phone;

}