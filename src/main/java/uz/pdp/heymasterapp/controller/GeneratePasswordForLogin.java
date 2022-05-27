package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.dto.LoginDto;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.repository.UserRepository;

import java.util.Optional;

@RequestMapping("/api/password")
@RestController
@RequiredArgsConstructor
public class GeneratePasswordForLogin {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    @PostMapping()
    public ResponseEntity getPassword(@RequestBody LoginDto loginDto){
        Optional<User> optionalUser = userRepository.findByPhoneNumber(loginDto.getPhoneNumber());
        ApiResponse apiResponse = new ApiResponse();
        Integer generate = generate();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setGeneratePassword(passwordEncoder.encode(String.valueOf(generate)));
            apiResponse.setMessage(" go to login page ");
            apiResponse.setSuccess(true);
            apiResponse.setObject(generate);
            userRepository.save(user);
        }
        else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("You must registered");
            apiResponse.setObject(generate);
        }


        System.out.println(generate);
        return ResponseEntity.ok().body(apiResponse);
    }

    public Integer generate(){
        int smsCode = (int) ((Math.random() * 9000) + 900);
        return  smsCode;
    }
}
