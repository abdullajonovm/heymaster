package uz.pdp.heymasterapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.dto.RegisterForClientDto;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.repository.AttachmentRepository;
import uz.pdp.heymasterapp.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository userRepository;
    final AttachmentRepository attachmentRepository;

    public ApiResponse edit(User user, RegisterForClientDto dto) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(dto.getPhoneNumber());
        if (!optionalUser.isPresent())  return new ApiResponse("This number already exist ",false);
        user.setFullName(dto.getFullName());
//        user.setAttachments(Collections.singletonList(attachmentRepository.findByProfilePhoto(true)));
        user.setPhoneNumber(dto.getPhoneNumber());
        userRepository.save(user);
        return new ApiResponse("Edited",true);
    }
}
