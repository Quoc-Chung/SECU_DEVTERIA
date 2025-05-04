package com.chungquoc.xtpqredis.controller;

import com.chungquoc.xtpqredis.dto.request.UserCreationRequest;
import com.chungquoc.xtpqredis.dto.request.UserUpdateRequest;
import com.chungquoc.xtpqredis.dto.response.ApiResponse;
import com.chungquoc.xtpqredis.dto.response.GeneralResponse;
import com.chungquoc.xtpqredis.dto.response.ResponseFactory;
import com.chungquoc.xtpqredis.dto.response.UserResponse;
import com.chungquoc.xtpqredis.entity.User;
import com.chungquoc.xtpqredis.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  private final ModelMapper modelMapper;


  /*0 Trả về một danh sách user -*/
  @GetMapping
  public ResponseEntity<ApiResponse<List<User>>> getUsers() {
    ApiResponse apiResponse = new ApiResponse<>();
    apiResponse.setData(userService.getUsers());
    apiResponse.setMessage(null);
    return ResponseEntity.ok(apiResponse);
  }

  /*- Cái get hiện tại nó đang trả về user nhưng tôi không muốn thế, tôi muốn trả về UserResponse thì làm như nào  -*/
  @GetMapping("/{userId}")
  public ResponseEntity<GeneralResponse<UserResponse>> getUser(@PathVariable("userId") String userId){
    UserResponse userResponse = modelMapper.map(userService.getUser(userId), UserResponse.class);
    return ResponseFactory.success(userResponse);
  }

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody @Valid UserCreationRequest request){
    return ResponseFactory.success(userService.createUser(request));
  }

  @PutMapping("/{userId}")
  ResponseEntity<GeneralResponse<User>>  updateUserTrue(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
    return ResponseFactory.success(userService.updateUser(userId, request));
  }

  @DeleteMapping("/{userId}")
  String deleteUser(@PathVariable String userId){
    userService.deleteUser(userId);
    return "User has been deleted";
  }



}