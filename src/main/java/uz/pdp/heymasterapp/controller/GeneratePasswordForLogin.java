package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.heymasterapp.dto.*;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.feignClient.SendMassage;
import uz.pdp.heymasterapp.feignClient.SendNotification;
import uz.pdp.heymasterapp.repository.UserRepository;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/api/password")
@RestController
@RequiredArgsConstructor
public class GeneratePasswordForLogin  {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    final SendMassage sendMassage;




    @Value("${key.for.Send.SMS}")
    private String SMS_Key;

    @PostMapping()
    public ResponseEntity getPassword(@Valid @RequestBody LoginDto loginDto) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(loginDto.getPhoneNumber());
        ApiResponse apiResponse = new ApiResponse();
        Integer generate = generate();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setGeneratePassword(passwordEncoder.encode(String.valueOf(generate)));
            apiResponse.setMessage(" go to login page ");
            apiResponse.setSuccess(true);
            apiResponse.setObject(generate);
            SendMassageDto sendMassageDto = new SendMassageDto();
            sendMassageDto.setRecipients(loginDto.getPhoneNumber());
            sendMassageDto.setBody(sendMassageDto.getBody() + " your code " + generate);
           // sendMassage.sendMassages(sendMassageDto,SMS_Key);
            userRepository.save(user);
        } else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("You must registered");
            apiResponse.setObject(generate);
            SendMassageDto sendMassageDto = new SendMassageDto();
            sendMassageDto.setBody(sendMassageDto.getBody());
            sendMassageDto.setRecipients(loginDto.getPhoneNumber());
//            sendMassage.sendMassages(sendMassageDto, key);
        }


        System.out.println(generate);
        return ResponseEntity.ok().body(apiResponse);
    }


    public Integer generate() {
        int smsCode = (int) ((Math.random() * 9000) + 900);
        return smsCode;
    }


}
