package com.chungquoc.xtpqredis.controller;

import com.chungquoc.xtpqredis.dto.request.AuthenticationRequest;
import com.chungquoc.xtpqredis.dto.request.UserCreationRequest;
import com.chungquoc.xtpqredis.dto.response.AuthenticationResponse;
import com.chungquoc.xtpqredis.dto.response.GeneralResponse;
import com.chungquoc.xtpqredis.dto.response.ResponseFactory;
import com.chungquoc.xtpqredis.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<GeneralResponse<AuthenticationResponse>> createUser(@RequestBody @Valid AuthenticationRequest request){
    return ResponseFactory.success(authenticationService.authenticate(request));
  }

}
