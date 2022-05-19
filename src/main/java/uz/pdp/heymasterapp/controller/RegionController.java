package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.heymasterapp.entity.location.Region;
import uz.pdp.heymasterapp.repository.RegionRepository;

import java.util.List;

@RestController
@RequestMapping("/api/region")
@RequiredArgsConstructor
public class RegionController {
    final RegionRepository repository;

    @GetMapping("/all")
    public ResponseEntity getAll(){
        List<Region> regionList = repository.findAll();
        return ResponseEntity.ok().body(regionList);
    }
    @GetMapping("/all/uz")
    public ResponseEntity getAllUzName(){
        List<Region> regionList = repository.getAllUzName();
        return ResponseEntity.ok().body(regionList);
    }
     @GetMapping("/all/ru")
    public ResponseEntity getAllRUName(){
        List<Region> regionList = repository.getAllRuName();
        return ResponseEntity.ok().body(regionList);
    }


}
