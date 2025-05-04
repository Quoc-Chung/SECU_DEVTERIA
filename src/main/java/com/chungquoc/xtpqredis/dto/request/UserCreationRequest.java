package com.chungquoc.xtpqredis.dto.request;

import com.chungquoc.xtpqredis.validation.Phone;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
   Long id;
   @Size(min = 3, max = 50, message = "password must be at leastt 8 kt")
   String username;
   @Size(min=8 , max=17 , message = "Password must have to lease 8 charater")
   String password;

   String firstName;
   String lastName;

  @Phone()
   String phone;

  @NotNull(message="BirthofDate must be not null")
  @DateTimeFormat (iso= ISO.DATE)
  @JsonFormat(pattern = "MM/dd/yyyy")
   LocalDate dob;


}
