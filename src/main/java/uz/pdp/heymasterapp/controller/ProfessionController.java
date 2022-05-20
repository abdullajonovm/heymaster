package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.heymasterapp.dto.ProfessionDto;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.service.ProfessionService;

import javax.validation.Valid;

@RequestMapping("/api/profession")
@RestController
@RequiredArgsConstructor
public class ProfessionController {

    final ProfessionService professionService;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity getAll() {
        ApiResponse apiResponse = professionService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','CLIENT')")
    @GetMapping("/getAllActive")
    public ResponseEntity getAllActive(){
        ApiResponse apiResponse=professionService.getAllActive();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        ApiResponse apiResponse = professionService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','CLIENT')")
    @GetMapping("/search/{name}")
    public ResponseEntity searchByChar(@PathVariable String name) {
        ApiResponse apiResponse = professionService.getByChar(name);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @PostMapping("")
    public ResponseEntity add(@RequestBody ProfessionDto professionDto) {
        ApiResponse apiResponse = professionService.add(professionDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','MASTER')")
    @PatchMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id,@Valid @RequestBody ProfessionDto professionDto){
        ApiResponse apiResponse=professionService.edit(id,professionDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity del(@PathVariable Integer id){
        ApiResponse apiResponse=professionService.del(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

}
