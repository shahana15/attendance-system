package com.threeaxislabs.ams.controller;

import com.threeaxislabs.ams.dto.AuthRequest;
import com.threeaxislabs.ams.dto.GenericResponse;
import com.threeaxislabs.ams.entity.UserDetail;
import com.threeaxislabs.ams.service.JwtService;
import com.threeaxislabs.ams.service.UserService;
import com.threeaxislabs.ams.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
@Slf4j
public class AuthController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public UserDetail addNewUser(@RequestBody UserDetail userDetail) {
        return userService.addUser(userDetail);
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse> authenticate(@RequestBody AuthRequest authRequest) {
        log.info("Auth request-authenticate {} ", Utils.printJson(authRequest));
        GenericResponse genericResponse = new GenericResponse();
        HttpStatus STATUS = null;
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                String accessToken = jwtService.generateToken(authRequest.getUsername());
                Map<String, Object> response = new HashMap<>();
                response.put("access_token", accessToken);
                genericResponse.setData(response);
                genericResponse.setStatus(200);
                STATUS = HttpStatus.OK;
            } else {
                genericResponse.setData("UnAuthorized");
                genericResponse.setStatus(401);
                STATUS = HttpStatus.UNAUTHORIZED;
            }
        } catch (Exception e) {
            log.error("Exception occurred while authenticate", e);
            if (e instanceof BadCredentialsException) {
                genericResponse.setData("UnAuthorized");
                genericResponse.setStatus(401);
            }
            STATUS = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<>(genericResponse, STATUS);
    }
}
