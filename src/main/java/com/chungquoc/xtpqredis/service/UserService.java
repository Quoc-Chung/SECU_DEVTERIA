package com.chungquoc.xtpqredis.service;

import com.chungquoc.xtpqredis.dto.request.UserCreationRequest;
import com.chungquoc.xtpqredis.dto.request.UserUpdateRequest;
import com.chungquoc.xtpqredis.dto.response.AppException;
import com.chungquoc.xtpqredis.dto.response.UserResponse;
import com.chungquoc.xtpqredis.entity.User;
import com.chungquoc.xtpqredis.repository.UserRepository;
import com.chungquoc.xtpqredis.utils.enums.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  private final ModelMapper mapper;


  /*- Tạo ra một user -*/
  /*
      {
        "username": "quocchung",
        "password": "password123",
        "firstName": "tu",
        "lastName": "chung",
        "dob": "1990-05-12",
        "phone":"0233"
      }
  */
  public User createUser(UserCreationRequest request){
    User user = new User();

    /*- db có = true ,không có = false -*/
    /*- Nếu như tên đã có rồi thì không thể tao them cai moi nao nua -*/
    if(userRepository.existsByUsername(request.getUsername())){
      throw new AppException(ErrorCode.USER_EXISTS);
    }
    /*- - Chuyển đoi giua user va DTO -*/
    //    user.setUsername(request.getUsername());
    //    user.setPassword(request.getPassword());
    //    user.setFirstName(request.getFirstName());
    //    user.setLastName(request.getLastName());
    //    user.setPhone(request.getPhone());
    //    user.setDob(request.getDob());

     user = mapper.map(request, User.class);
    return userRepository.save(user);
  }

  /*- Sửa đổi user, chỉ sửa đổi một só trường thôi, còn lại thì giu nguyen -*/
  /*
       {
          "password": "newPassword123",
          "firstName": "Chungupdate",
          "lastName": "Chung",
          "dob": "1995-08-15",
          "phone":"02222"
        }
  */

  public User updateUser(String userId, UserUpdateRequest request) {
    User user = getUser(userId);

    /*- Hoặc có thể sử dụng model mapper để chuyển đổi -*/
    user.setId(userId);
    user.setPassword(request.getPassword());
    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    user.setDob(request.getDob());
    user.setPhone(request.getPhone());

    return userRepository.save(user);
  }


  /*- Xóa một user theo id của user đó -*/
  public void deleteUser(String userId){
    userRepository.deleteById(userId);
  }

  /*- Lấy ra danh sách user -*/
  public List<User> getUsers(){
    return userRepository.findAll();
  }

  /*- Lấy ra một user theo id -*/
  public User getUser(String id){
    return userRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));

  }

}
