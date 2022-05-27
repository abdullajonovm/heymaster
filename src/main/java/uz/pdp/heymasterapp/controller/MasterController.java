package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.dto.RegisterForClientDto;
import uz.pdp.heymasterapp.dto.RegisterForMasterDto;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.security.CurrentUser;
import uz.pdp.heymasterapp.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/master")
public class MasterController {

    final UserService userService;

    @PreAuthorize(value = "hasAnyAuthority('CLIENT','SUPER_ADMIN')")
    @PutMapping("/edit/{id}")
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

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN')")
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

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','CLIENT')")
    @GetMapping("/deleteAttPhoto/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        ApiResponse apiResponse=userService.deleteAttPhoto(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 200:404).body(apiResponse);
    }
}