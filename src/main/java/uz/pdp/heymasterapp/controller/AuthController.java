package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.dto.LoginDto;
import uz.pdp.heymasterapp.dto.RegisterForClientDto;
import uz.pdp.heymasterapp.dto.RegisterForMasterDto;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.entity.enums.LanguageEnum;
import uz.pdp.heymasterapp.security.CurrentUser;
import uz.pdp.heymasterapp.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/client/register")
    public HttpEntity<?> register(@Valid @RequestBody RegisterForClientDto registerDto) {
        ApiResponse response = authService.register(registerDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PostMapping("/master/register")
    public HttpEntity<?> registerForMaster(@Valid @RequestBody RegisterForMasterDto registerDto) {
        ApiResponse response = authService.registerForMaster(registerDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        ApiResponse response = authService.login(loginDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 401).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity current(@CurrentUser User user) {

        return ResponseEntity.ok(user);
    }


}
