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
import uz.pdp.heymasterapp.entity.Role;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.entity.enums.RoleEnum;
import uz.pdp.heymasterapp.entity.location.District;
import uz.pdp.heymasterapp.entity.location.Location;
import uz.pdp.heymasterapp.entity.location.Region;
import uz.pdp.heymasterapp.repository.*;
import uz.pdp.heymasterapp.security.JwtProvider;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;


    private final RoleRepository roleRepository;

    final LocationRepository locationRepository;
    final DistrictRepository districtRepository;
    final RegionRepository regionRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository,
                       LocationRepository locationRepository, DistrictRepository districtRepository,
                       RegionRepository regionRepository, @Lazy AuthenticationManager authenticationManager,
                       JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.locationRepository = locationRepository;
        this.districtRepository = districtRepository;
        this.regionRepository = regionRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public ApiResponse register(@Valid RegisterForClientDto registerDto) {

        boolean existsByEmail = userRepository.existsByPhoneNumber(registerDto.getPhoneNumber());
        if (existsByEmail) {
            return new ApiResponse("This user already exist",false);
        }
        User user = new User();
        user.setPhoneNumber(registerDto.getPhoneNumber());
        user.setFullName(registerDto.getFullName());
        Role role = roleRepository.findByRoleName(RoleEnum.CLIENT);
        user.setRoles(role);
        user.setGender(registerDto.getGender());
        user.setBirthDate(registerDto.getDate());
        userRepository.save(user);


        return new ApiResponse("Successfully registered. Please verify",true);
    }



    public ApiResponse login(LoginDto loginDto) {

        try {
            if (!userRepository.findByPhoneNumber(loginDto.getPhoneNumber()).isPresent())
                return new ApiResponse("user  not found",false);
                Authentication authenticate = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken
                    (loginDto.getPhoneNumber(),loginDto.getPassword()));

          //  User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getPhoneNumber());
            return new ApiResponse("Mana token",true,token);
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
        userRepository.save(user);

        return new ApiResponse("Master successfully saved !",true);

    }
}
