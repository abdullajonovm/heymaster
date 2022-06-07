package uz.pdp.heymasterapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.dto.RegisterForClientDto;
import uz.pdp.heymasterapp.entity.Attachment;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.repository.AttachmentRepository;
import uz.pdp.heymasterapp.repository.UserRepository;

import java.util.Collections;
import java.util.List;
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
        user.setBirthDate(dto.getDate());
        user.setGender(dto.getGender());
//        Optional<Attachment> attachmentOptional = attachmentRepository.findById(dto.getAttachmentId());
//        if (!attachmentOptional.isPresent()) return new ApiResponse("Profile photo not found ",false);
//        user.setProfilePhoto(attachmentOptional.get());
        user.setPhoneNumber(dto.getPhoneNumber());
        userRepository.save(user);

        return new ApiResponse("Edited",true);
    }

    public ApiResponse getAll() {
        List<User> userList = userRepository.findAll();
        return new ApiResponse("Ok",true,userList);
    }

    public ApiResponse getAllActive() {
        Optional<List<User>> optional = userRepository.findAllByActiveIsTrue();
        if (!optional.isPresent()) return new ApiResponse("Not active user ",true);
        List<User> userList = optional.get();
        return new ApiResponse("ok",true,userList);
    }

    public ApiResponse block(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return new ApiResponse("User not found",false);
        User user = optionalUser.get();
        user.setActive(false);
        userRepository.save(user);
        return new ApiResponse("User bloked",true);

    }

    public ApiResponse unblock(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return new ApiResponse("User not found",false);
        User user = optionalUser.get();
        user.setActive(true);
        userRepository.save(user);
        return new ApiResponse("User bloked",true);
    }

    public ApiResponse deleteAttPhoto(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return new ApiResponse("User not found",false);
        User user = optionalUser.get();
        Attachment profilePhoto = user.getProfilePhoto();
        attachmentRepository.delete(profilePhoto);
        userRepository.save(user);
        return new ApiResponse("Profile photo deleted",true);

    }
}
