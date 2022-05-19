package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        if (!optionalUser.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body("You must registired");
        Integer generate = generate();
        User user = optionalUser.get();
        System.out.println(generate);
        user.setGeneratePassword(passwordEncoder.encode(String.valueOf(generate)));
        userRepository.save(user);
        System.out.println(generate);
        return ResponseEntity.ok().body( "bu sms orqali jonatish kk edi: "+generate);
    }

    public Integer generate(){
        int smsCode = (int) ((Math.random() * 9000) + 900);
        return  smsCode;
    }
}
