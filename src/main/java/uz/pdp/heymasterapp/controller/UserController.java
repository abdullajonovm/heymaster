package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.dto.RegisterForClientDto;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.entity.location.District;
import uz.pdp.heymasterapp.repository.UserRepository;
import uz.pdp.heymasterapp.security.CurrentUser;
import uz.pdp.heymasterapp.service.UserService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {


    final UserRepository userRepository;
    final UserService userService;

    @PreAuthorize(value = "hasAnyAuthority('CLIENT','SUPER_ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity editeUserProfile(@CurrentUser User user, @RequestBody RegisterForClientDto dto) {
        ApiResponse response = userService.edit(user, dto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity getAllUser() {
        ApiResponse apiResponse = userService.getAll();
        return ResponseEntity.ok().body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN')")
    @GetMapping("/allActive")
    public ResponseEntity getAllActiveUser() {
        ApiResponse apiResponse = userService.getAllActive();
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

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','CLIENT')")
    @GetMapping("/deleteAttPhoto/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        ApiResponse apiResponse=userService.deleteAttPhoto(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 200:404).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('MASTER','SUPER_ADMIN','CLIENT')")
    @GetMapping("/search/{name}")
    public ResponseEntity getMaster(@PathVariable String name){
        Optional<List<District>> optional = userRepository.getClientByFullName(name);
        if (optional.isPresent()) return ResponseEntity.ok().body(optional.get());
        return ResponseEntity.ok().body("Not found");
    }
}