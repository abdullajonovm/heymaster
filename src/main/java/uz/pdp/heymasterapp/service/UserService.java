package uz.pdp.heymasterapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.dto.MasterEditDto;
import uz.pdp.heymasterapp.dto.RegisterForClientDto;
import uz.pdp.heymasterapp.dto.RegisterForMasterDto;
import uz.pdp.heymasterapp.entity.Attachment;
import uz.pdp.heymasterapp.entity.Profession;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.entity.enums.RoleEnum;
import uz.pdp.heymasterapp.entity.location.District;
import uz.pdp.heymasterapp.entity.location.Location;
import uz.pdp.heymasterapp.entity.location.Region;
import uz.pdp.heymasterapp.repository.*;

import java.util.ArrayList;
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
    final RoleRepository roleRepository;

    final AttachmentRepository attachmentRepository;

    public ApiResponse edit(User user, RegisterForClientDto dto) {
        user.setFullName(dto.getFullName());
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

   public ApiResponse editMaster(User user, MasterEditDto dto) {

        user.setFullName(dto.getFullName());
        Location location = user.getLocation();
        Optional<District> districtOptional = districtRepository.findById(dto.getDistrictId());
        if (!districtOptional.isPresent()) return new ApiResponse("District not found", false);
        Optional<Region> regionOptional = regionRepository.findById(dto.getRegionId());
        if (!regionOptional.isPresent()) return new ApiResponse("Region not found", false);
        location.setDistrict(districtOptional.get());
        location.setRegion(regionOptional.get());
        user.setLocation(location);
        List<Profession> professions = new ArrayList<>();
       for (Integer integer : dto.getProfessionList()) {
           Optional<Profession> byId = professionRepository.findById(integer);
           byId.ifPresent(professions::add);
       }
        user.setProfessionList(professions);
        user.setExperienceYear(dto.getExperienceYear());

        userRepository.save(user);
        return new ApiResponse("Edited master account ", true);

    }


    public ApiResponse clientToMaster(User user, RegisterForMasterDto dto) {
        Optional<User> optional = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (!optional.isPresent()) return new ApiResponse("User not found",false);
        user.setRoles(roleRepository.findByRoleName(RoleEnum.MASTER));
        Optional<Region> regionOptional = regionRepository.findById(dto.getRegionId());
        if (!regionOptional.isPresent()) return new ApiResponse("Region not found",false);
        Region region = regionOptional.get();
        Optional<District> districtOptional = districtRepository.findById(dto.getDistrictId());
        if (!districtOptional.isPresent()) return new ApiResponse("District not found",false);
        District district = districtOptional.get();
        Location location = new Location();
        location.setRegion(region);
        location.setDistrict(district);
        user.setLocation(location);
        user.setExperienceYear(dto.getExperienceYear());

        user.setSalary(dto.getSalary());

        List<Integer> professionIdList = dto.getProfessionIdList();
        List<Profession>professions=new ArrayList<>();
        for (int i = 0; i < professionIdList.size(); i++) {
            Optional<Profession> optional1 = professionRepository.findById(professionIdList.get(i));
            if (!optional.isPresent()) return new ApiResponse("Profession not found",false);
            Profession profession = optional1.get();
            professions.add(profession);
        }
        user.setProfessionList(professions);

        userRepository.save(user);
        return new ApiResponse("Client profile changed to master",true);
    }

    public ApiResponse masterToClient(User user) {
        Optional<User> optional = userRepository.findById(user.getId());
        if (!optional.isPresent()) return new ApiResponse("Master not found",false);
        user.setAlreadyIsMaster(true);
        user.setRoles(roleRepository.findByRoleName(RoleEnum.CLIENT));
        userRepository.save(user);
        return new ApiResponse("master client ga aylandi ",true);
    }

    public ApiResponse editProfile(User user, Long id) {
        Optional<User> optional = userRepository.findById(user.getId());
        if (optional.isPresent()){
            Optional<Attachment> attachmentOptional = attachmentRepository.findById(id);
            if (!attachmentOptional.isPresent()) return new ApiResponse("Photo not found",false);
            user.setProfilePhoto(attachmentOptional.get());
            userRepository.save(user);
            return new ApiResponse("Profile photo edited ",true);
        }
        return new ApiResponse("User not found ",false);
    }

    public ApiResponse getById(Long id) {

        Optional<User> optionalUser = userRepository.getByMasterId(id);

        if (optionalUser.isPresent()){
            return new ApiResponse("Mana ",true ,optionalUser.get());
        }
        return new ApiResponse("Master not found ",false );
    }

    public ApiResponse editProfileNumber(User user, String number) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(number);
        if (!optionalUser.isPresent()) return new ApiResponse("raqam mavjud",false);
        user.setPhoneNumber(number);
        userRepository.save(user);
        return new ApiResponse("Edit number ",true);
    }
}
