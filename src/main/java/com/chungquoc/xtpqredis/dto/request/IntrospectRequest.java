package com.chungquoc.xtpqredis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*- Dùng để kiểm tra xem token hợp lệ không  -*/
public class IntrospectRequest {
      String token;
}
