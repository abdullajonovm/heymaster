package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.dto.RegisterForMasterDto;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.entity.enums.RoleEnum;
import uz.pdp.heymasterapp.entity.location.District;
import uz.pdp.heymasterapp.repository.RoleRepository;
import uz.pdp.heymasterapp.repository.UserRepository;
import uz.pdp.heymasterapp.security.CurrentUser;
import uz.pdp.heymasterapp.service.UserService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/master")
public class MasterController {

    final UserService userService;
    final UserRepository userRepository;

    final RoleRepository roleRepository;

    @PreAuthorize("hasAnyAuthority('MASTER','SUPER_ADMIN','CLIENT')")
    @GetMapping("/search/{name}")
    public ResponseEntity getMaster(@PathVariable String name){
        Optional<List<District>> optional = userRepository.getMasterByFullName(name);
        if (optional.isPresent()) return ResponseEntity.ok().body(optional.get());
        return ResponseEntity.ok().body("Not found");
    }
    @PreAuthorize("hasAnyAuthority('MASTER','SUPER_ADMIN','CLIENT')")
    @GetMapping("/search/region/{id}")
    public ResponseEntity getMaster(@PathVariable Integer id){
        Optional<List<User>> optional = userRepository.getMasterByRegionId(id);
        if (optional.isPresent()) return ResponseEntity.ok().body(optional.get());
        return ResponseEntity.ok().body("Not found");
    }
    @PreAuthorize("hasAnyAuthority('MASTER','SUPER_ADMIN','CLIENT')")
    @GetMapping("/search/district/{id}")
    public ResponseEntity getMasterByDistrictId(@PathVariable Integer id){
        Optional<List<User>> optional = userRepository.getMasterByDistrictId(id);
        if (optional.isPresent()) return ResponseEntity.ok().body(optional.get());
        return ResponseEntity.ok().body("Not found");
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN','MASTER')")
    @PutMapping("/edit")
    public ResponseEntity editeUserProfile(@CurrentUser User user, @RequestBody RegisterForMasterDto dto) {
        ApiResponse response = userService.editMaster(user, dto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity getAllUser() {
        ApiResponse apiResponse = userService.getAllMaster();
        return ResponseEntity.ok().body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN','CLIENT','MASTER')")
    @GetMapping("/allActive")
    public ResponseEntity getAllActiveUser() {
        ApiResponse apiResponse = userService.getAllMasterActive();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN')")
    @GetMapping("/block/{id}")
    public ResponseEntity addBlock(@PathVariable Long id) {
        ApiResponse apiResponse = userService.block(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN')")
    @GetMapping("/unblock/{id}")
    public ResponseEntity unblock(@PathVariable Long id) {
        ApiResponse apiResponse = userService.unblock(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
//TODO qaraw kk
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    @GetMapping("/deleteAttPhoto/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        ApiResponse apiResponse=userService.deleteAttPhoto(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 200:404).body(apiResponse);
    }
    @PreAuthorize("hasAnyAuthority('MASTER','SUPER_ADMIN')")
    @PostMapping("/to/client")
    public ResponseEntity clientToMaster(@CurrentUser User user){
        ApiResponse apiResponse=userService.masterToClient(user);
        return ResponseEntity.status(apiResponse.isSuccess()? 200:404).body(apiResponse);
    }
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','CLIENT','MASTER')")
    @GetMapping("/{id}")
    public ResponseEntity getOneMaster(@PathVariable Long id){
        ApiResponse apiResponse=userService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 200:404).body(apiResponse);
    }
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','CLIENT','MASTER')")
    @GetMapping("/roleToMaster")
    public ResponseEntity setRole(@CurrentUser User user){
        user.setRoles(roleRepository.findByRoleName(RoleEnum.MASTER));
        userRepository.save(user);
        ApiResponse apiResponse = new ApiResponse("Success", true);
        return ResponseEntity.status(apiResponse.isSuccess()? 200:404).body(apiResponse);
    }


}