package uz.pdp.heymasterapp.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.dto.LoginDto;
import uz.pdp.heymasterapp.dto.RegisterForClientDto;
import uz.pdp.heymasterapp.dto.RegisterForMasterDto;
import uz.pdp.heymasterapp.entity.Device;
import uz.pdp.heymasterapp.entity.Profession;
import uz.pdp.heymasterapp.entity.Role;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.entity.enums.RoleEnum;
import uz.pdp.heymasterapp.entity.location.District;
import uz.pdp.heymasterapp.entity.location.Location;
import uz.pdp.heymasterapp.entity.location.Region;
import uz.pdp.heymasterapp.repository.*;
import uz.pdp.heymasterapp.security.JwtProvider;

import javax.validation.Valid;
import java.util.*;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;


    private final RoleRepository roleRepository;

    final LocationRepository locationRepository;
    final DistrictRepository districtRepository;
    final RegionRepository regionRepository;

    final DeviceRepository deviceRepository;
    final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;
    private final ProfessionRepository professionRepository;


    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository,
                       LocationRepository locationRepository, DistrictRepository districtRepository,
                       RegionRepository regionRepository, DeviceRepository deviceRepository, PasswordEncoder passwordEncoder1, @Lazy AuthenticationManager authenticationManager,
                       JwtProvider jwtProvider, ProfessionRepository professionRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.locationRepository = locationRepository;
        this.districtRepository = districtRepository;
        this.regionRepository = regionRepository;
        this.deviceRepository = deviceRepository;
        this.passwordEncoder = passwordEncoder1;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.professionRepository = professionRepository;
    }

    public ApiResponse register(@Valid RegisterForClientDto registerDto) {

        boolean existsByEmail = userRepository.existsByPhoneNumber(registerDto.getPhoneNumber());
        if (existsByEmail) {
            return new ApiResponse("This user already exist",false);
        }
        User user = new User();
        user.setGeneratePassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setPhoneNumber(registerDto.getPhoneNumber());
        user.setFullName(registerDto.getFullName());
        Role role = roleRepository.findByRoleName(RoleEnum.CLIENT);
        user.setRoles(role);
        Device device = new Device();
        device.setDeviceId(registerDto.getDeviceId());
        device.setDeviceLan(registerDto.getDeviceLan());
        user.setDevice(device);
        deviceRepository.save(device);
        user.setGender(registerDto.getGender());
        user.setBirthDate(registerDto.getDate());
        userRepository.save(user);


        return new ApiResponse("Successfully registered. Please verify",true);
    }



    public ApiResponse login(LoginDto loginDto) {
        Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(loginDto.getPhoneNumber());
        try {
            if (!byPhoneNumber.isPresent())
                return new ApiResponse("user  not found",false);
                Authentication authenticate = authenticationManager.
                   authenticate(new UsernamePasswordAuthenticationToken
                    (loginDto.getPhoneNumber(),loginDto.getPassword()));

          //  User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getPhoneNumber());
            return new ApiResponse(""+byPhoneNumber.get().getRoles().getRoleName(),true,token);
        }catch (BadCredentialsException badCredentialsException){
            return new ApiResponse("Username or password is wrong",false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(username).orElseThrow(() ->
                new UsernameNotFoundException(username+" not found"));
    }

    public ApiResponse registerForMaster(RegisterForMasterDto registerDto) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(registerDto.getPhoneNumber());
        if (optionalUser.isPresent())return new ApiResponse("This user already exist",false);

        User user = new User();
        user.setAlreadyIsMaster(true);
        user.setGeneratePassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setFullName(registerDto.getFullName());
        user.setPhoneNumber(registerDto.getPhoneNumber());
        Role role = roleRepository.findByRoleName(RoleEnum.MASTER);
        user.setRoles(role);
        user.setExperienceYear(registerDto.getExperienceYear());
        user.setSalary(registerDto.getSalary());
        user.setGender(registerDto.getGender());
        user.setBirthDate(registerDto.getBirthDate());
        Optional<District> districtOptional = districtRepository.findById(registerDto.getDistrictId());
        if (!districtOptional.isPresent()) return new ApiResponse("District not found",false);
        Optional<Region> regionOptional = regionRepository.findById(registerDto.getRegionId());
        if (!regionOptional.isPresent()) return new ApiResponse("Region not found",false);
        Location location = new Location();
        location.setDistrict(districtOptional.get());
        location.setRegion(regionOptional.get());
        user.setLocation(location);
        Device device = new Device();
        device.setDeviceId(registerDto.getDeviceId());
        device.setDeviceLan(registerDto.getDeviceLan());
        deviceRepository.save(device);
        user.setDevice(device);
        List<Integer> professionIdList = registerDto.getProfessionIdList();
        List<Profession>professions=new ArrayList<>();
        for (int i = 0; i < professionIdList.size(); i++) {
            Optional<Profession> optional = professionRepository.findById(professionIdList.get(i));
            if (!optional.isPresent()) return new ApiResponse("Profession not found",false);
            Profession profession = optional.get();
           professions.add(profession);
        }
        user.setProfessionList(professions);


        userRepository.save(user);
        return new ApiResponse("Master successfully saved !",true);

    }
}
