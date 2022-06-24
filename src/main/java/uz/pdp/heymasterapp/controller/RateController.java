package uz.pdp.heymasterapp.controller;

import com.google.protobuf.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.dto.RateDto;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.security.CurrentUser;
import uz.pdp.heymasterapp.service.RateService;

@Controller
@RequestMapping("/api/rate")
@RequiredArgsConstructor
public class RateController {
    final RateService rateService;

    @PostMapping
    public ResponseEntity addRate(@RequestBody RateDto rateDto, @CurrentUser User user){
        //TODO userga rateda kelgan Feedbackni yuborishimiz kerakmi
        ApiResponse apiResponse = rateService.addRate(rateDto,user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('SUPER_ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        ApiResponse apiResponse = rateService.getAll();
        return ResponseEntity.ok().body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('SUPER_ADMIN')")
    @GetMapping("/getOne")
    public ResponseEntity getOne(@RequestParam Long id){
        ApiResponse apiResponse = rateService.getId(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN','MASTER','CLIENT')")
    @GetMapping("/master")
    public ResponseEntity getAllMaster(@CurrentUser User user){
        ApiResponse apiResponse=rateService.getMasterRate(user) ;
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

}
