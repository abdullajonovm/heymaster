package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.heymasterapp.dto.HomPageDto;
import uz.pdp.heymasterapp.service.HomeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class
HomeInfoController {
    //TODO Get homePageInfo topProfessions+AllProfessions+popularMasters
    final HomeService homeService;

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','CLIENT', 'MASTER')")
    @GetMapping
    public ResponseEntity getHomeInfo(){
        HomPageDto homPageDto = homeService.getHomeInfo();
        return ResponseEntity.ok().body(homPageDto);
    }
}
