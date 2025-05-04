package com.chungquoc.xtpqredis.mapper.modelMapper;

import com.chungquoc.xtpqredis.dto.response.UserResponse;
import com.chungquoc.xtpqredis.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);

    /*- Chuyển đổi từ User -> UserResponse -*/
    modelMapper.typeMap(User.class, UserResponse.class).addMappings(mapper -> {
      mapper.map(User::getPhone, UserResponse::setSoDienThoai);
      mapper.map(User::getDob, UserResponse::setNgaySinh);
    });

    return modelMapper;
  }
}
