package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.heymasterapp.entity.location.District;
import uz.pdp.heymasterapp.entity.location.Region;
import uz.pdp.heymasterapp.repository.DistrictRepository;
import uz.pdp.heymasterapp.repository.RegionRepository;

import java.util.List;

@RestController
@RequestMapping("/api/district")
@RequiredArgsConstructor
public class DistrictController {
    final DistrictRepository repository;

    @GetMapping("/all")
    public ResponseEntity getAll(){
        List<District> regionList = repository.findAll();
        return ResponseEntity.ok().body(regionList);
    }
    @GetMapping("/all/uz")
    public ResponseEntity getAllUzName(){
        List<District> regionList = repository.getAllUzName();
        return ResponseEntity.ok().body(regionList);
    }
     @GetMapping("/all/ru")
    public ResponseEntity getAllRUName(){
        List<District> regionList = repository.getAllRuName();
        return ResponseEntity.ok().body(regionList);
    }


}
