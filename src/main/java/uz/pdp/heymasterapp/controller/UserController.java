package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.dto.RegisterForClientDto;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.security.CurrentUser;
import uz.pdp.heymasterapp.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    //TODO editProfile
    //TODO postMapping register with outh2
    //TODO GetMapping sendOTP generate OTP
    //TODO PostMapping verify OTP
    //TODO register PostMapping param(String Role) + DTO malumotlari(registerdan otkaziladi)

    final UserService userService;
    @PreAuthorize(value = "hasAnyRole('CLIENT','SUPER_ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity editeUserProfile(@CurrentUser User user, @RequestBody RegisterForClientDto dto) {
        ApiResponse response = userService.edit(user, dto);
        return ResponseEntity.status(response.isSuccess() ? 200 :404).body(response);
    }



}