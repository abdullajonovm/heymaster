package uz.pdp.heymasterapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.dto.RegisterForClientDto;
import uz.pdp.heymasterapp.dto.RegisterForMasterDto;
import uz.pdp.heymasterapp.entity.Attachment;
import uz.pdp.heymasterapp.entity.Profession;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.entity.location.District;
import uz.pdp.heymasterapp.entity.location.Location;
import uz.pdp.heymasterapp.entity.location.Region;
import uz.pdp.heymasterapp.repository.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository userRepository;

    final DistrictRepository districtRepository;

    final RegionRepository regionRepository;

    final LocationRepository location;

    final ProfessionRepository professionRepository;

    final AttachmentRepository attachmentRepository;

    public ApiResponse edit(User user, RegisterForClientDto dto) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(dto.getPhoneNumber());
        if (!optionalUser.isPresent()) return new ApiResponse("This number already exist ", false);
        user.setFullName(dto.getFullName());
//        Optional<Attachment> attachmentOptional = attachmentRepository.findById(dto.getAttachmentId());
//        if (!attachmentOptional.isPresent()) return new ApiResponse("Profile photo not found ",false);
//        user.setProfilePhoto(attachmentOptional.get());
        user.setPhoneNumber(dto.getPhoneNumber());
        userRepository.save(user);

        return new ApiResponse("Edited", true);
    }

    public ApiResponse getAll() {
        Optional<List<User>> optionalList = userRepository.getAllClient();
        if (!optionalList.isPresent()) return new ApiResponse("Not client now", false);
        List<User> userList = optionalList.get();
        return new ApiResponse("Ok", true, userList);
    }

    public ApiResponse getAllActive() {
        Optional<List<User>> optional = userRepository.findAllClientByActiveIsTrue();
        if (!optional.isPresent()) return new ApiResponse("Not active user ", true);
        List<User> userList = optional.get();
        return new ApiResponse("ok", true, userList);
    }

    public ApiResponse getAllMasterActive() {
        Optional<List<User>> optional = userRepository.findAllMasterByActiveIsTrue();
        if (!optional.isPresent()) return new ApiResponse("Not active user ", true);
        List<User> userList = optional.get();
        return new ApiResponse("ok", true, userList);
    }

    public ApiResponse block(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return new ApiResponse("User not found", false);
        User user = optionalUser.get();
        user.setActive(false);
        userRepository.save(user);
        return new ApiResponse("User bloked", true);

    }

    public ApiResponse unblock(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return new ApiResponse("User not found", false);
        User user = optionalUser.get();
        user.setActive(true);
        userRepository.save(user);
        return new ApiResponse("User bloked", true);
    }

    public ApiResponse deleteAttPhoto(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return new ApiResponse("User not found", false);
        User user = optionalUser.get();
        Attachment profilePhoto = user.getProfilePhoto();
        attachmentRepository.delete(profilePhoto);
        userRepository.save(user);
        return new ApiResponse("Profile photo deleted", true);

    }

    public ApiResponse getAllMaster() {
        Optional<List<User>> optionalList = userRepository.getAllMaster();
        if (!optionalList.isPresent()) return new ApiResponse("Not client now", false);
        List<User> userList = optionalList.get();
        return new ApiResponse("Ok", true, userList);
    }

   public ApiResponse editMaster(User user, RegisterForMasterDto dto) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(dto.getPhoneNumber());
        if (!optionalUser.isPresent()) return new ApiResponse("This number already exist ", false);
        user.setFullName(dto.getFullName());
        user.setPhoneNumber(dto.getPhoneNumber());
        Location location = user.getLocation();

        Optional<District> districtOptional = districtRepository.findById(dto.getDistrictId());
        if (!districtOptional.isPresent()) return new ApiResponse("District not found", false);
        Optional<Region> regionOptional = regionRepository.findById(dto.getRegionId());
        if (!regionOptional.isPresent()) return new ApiResponse("Region not found", false);
        location.setDistrict(districtOptional.get());
        location.setRegion(regionOptional.get());
        user.setLocation(location);
        List<Profession> professionList = user.getProfessionList();
        for (Integer integer : dto.getProfessionIdList()) {
            Profession profession = professionList.get(integer);
            professionList.add(profession);
            System.out.println(professionList);
        }
        user.setProfessionList(professionList);
        user.setExperienceYear(dto.getExperienceYear());
        userRepository.save(user);
        return new ApiResponse("Edited master accaunt ", true);

    }
}
