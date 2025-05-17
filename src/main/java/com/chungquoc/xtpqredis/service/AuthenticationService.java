package com.chungquoc.xtpqredis.service;

import com.chungquoc.xtpqredis.dto.request.AuthenticationRequest;
import com.chungquoc.xtpqredis.dto.request.IntrospectRequest;
import com.chungquoc.xtpqredis.dto.response.AppException;
import com.chungquoc.xtpqredis.dto.response.AuthenticationResponse;
import com.chungquoc.xtpqredis.dto.response.IntrospeactResponse;
import com.chungquoc.xtpqredis.entity.User;
import com.chungquoc.xtpqredis.repository.UserRepository;
import com.chungquoc.xtpqredis.utils.enums.ErrorCode;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;

import com.nimbusds.jwt.SignedJWT;
import java.text.ParseException;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

     private final UserRepository userRepository;


    @Value("${jwt.signer_key}")
    private String SIGNER_KEY; // Xóa @NonFinal nếu không cần



  /*- Hàm này so khớp xem mật khẩu nhập vào có trùng khớp với maajrt khẩu trong db không -*/
  public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest)
      {
    User user = userRepository.findByUsername(authenticationRequest.getUsername())
        .orElseThrow(() -> new AppException(ErrorCode.USER_EXISTS));

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    boolean authenticated =   passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
    if(!authenticated){
      throw new AppException(ErrorCode.UNAUTHENTICATED_EXCEPTION);
    }

    var token = generateToken(authenticationRequest.getUsername());

    return AuthenticationResponse.builder().token(token).authenticationResponse(authenticated).build();

  }



  /*- Hàm này kiểm tra xem token có hợp lệ không  -*/
  public IntrospeactResponse xac_thuc_tọken(IntrospectRequest request_token)
  throws JOSEException, ParseException {
     var token = request_token.getToken();

    JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

    SignedJWT signedJWT = SignedJWT.parse(token);
   var verified =  signedJWT.verify(verifier);

   /*- LẤy ra thời gian hết hạn token -*/
   Date  expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();
   return IntrospeactResponse.builder().valid( verified && expityTime.after(new Date())).build();
  }


  /* - HÀM TẠO TOKEN -*/
  private String generateToken(String username) {
    // 1. Header
    JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

    // 2. Payload
    JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
        .subject(username)
        .issuer("quocchung")
        .claim("userId", "custom")
        .issueTime(new Date())
        .expirationTime(new Date(System.currentTimeMillis() + 3600 * 1000))
        .build();

    Payload payload = new Payload(claimsSet.toJSONObject());

    JWSObject jwsObject = new JWSObject(header, payload);
    try{
      /*- KÝ -*/
      jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
      return jwsObject.serialize();

    }catch(JOSEException e){
      throw new RuntimeException(e);
    }


  }


}
