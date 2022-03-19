package com.heydieproject.restapimahasiswa.controller;

import com.heydieproject.restapimahasiswa.dto.AppUserAuthDto;
import com.heydieproject.restapimahasiswa.dto.ResponseDto;
import com.heydieproject.restapimahasiswa.entity.AppUserAuth;
import com.heydieproject.restapimahasiswa.service.serviceImpl.AppUserAuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class AppUserAuthController {

    @Autowired
    private AppUserAuthService appUserAuthService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto<AppUserAuth>> registerUser(@RequestBody AppUserAuthDto appUserAuthDto) {
        ResponseDto<AppUserAuth> responseData = new ResponseDto<>();
        AppUserAuth appUserAuth = modelMapper.map(appUserAuthDto, AppUserAuth.class);

        responseData.setStatus(true);
        responseData.setPayload(appUserAuthService.registerUserAuth(appUserAuth));
        responseData.getMessage().add("User registered succesfully");
        return ResponseEntity.ok(responseData);
    }
}
