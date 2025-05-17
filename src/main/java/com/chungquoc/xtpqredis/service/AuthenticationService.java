package com.chungquoc.xtpqredis.service;

import com.chungquoc.xtpqredis.dto.request.AuthenticationRequest;
import com.chungquoc.xtpqredis.dto.response.AppException;
import com.chungquoc.xtpqredis.dto.response.AuthenticationResponse;
import com.chungquoc.xtpqredis.entity.User;
import com.chungquoc.xtpqredis.repository.UserRepository;
import com.chungquoc.xtpqredis.utils.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

     private final UserRepository userRepository;


  public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
    User user = userRepository.findByUsername(authenticationRequest.getUsername())
        .orElseThrow(() -> new AppException(ErrorCode.USER_EXISTS));

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    authenticationResponse.setAuthenticationResponse(
        passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())
    );
    return authenticationResponse;
  }
}
