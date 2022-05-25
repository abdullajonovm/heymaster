package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.heymasterapp.dto.AdvertisingDto;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.entity.Advertising;
import uz.pdp.heymasterapp.service.AdvertisingService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/adversiting")
public class AdvertisingController {
    final AdvertisingService advertisingService;

    @PreAuthorize( "hasAuthority('SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity added(@RequestBody AdvertisingDto advertisingDto){
        ApiResponse apiResponse = advertisingService.addAdvertising(advertisingDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PreAuthorize( "hasAuthority('SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity edited(@PathVariable Long id, @RequestBody AdvertisingDto advertisingDto){
        ApiResponse apiResponse = advertisingService.edited(id, advertisingDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PreAuthorize( "hasAuthority('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleted(@PathVariable Long id){
        ApiResponse apiResponse = advertisingService.deleted(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity getAll(){
        ApiResponse apiResponse = advertisingService.getAll();
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        ApiResponse apiResponse = advertisingService.getOne(id);
        return ResponseEntity.ok().body(apiResponse);
    }



}
