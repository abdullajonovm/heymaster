package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.heymasterapp.entity.location.Region;
import uz.pdp.heymasterapp.repository.RegionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/region")
@RequiredArgsConstructor
public class RegionController {
    final RegionRepository repository;
//    @PreAuthorize(value = "hasAnyRole('MASTER')")
    @GetMapping("/all")
    public ResponseEntity getAll(){
        List<Region> regionList = repository.findAll();
        return ResponseEntity.ok().body(regionList);
    }
//    @PreAuthorize("hasAnyAuthority('MASTER','SUPER_ADMIN','CLIENT')")
    @GetMapping("/search/{name}")
    public ResponseEntity getRegionByName(@PathVariable String name){
        Optional<List<Region>> optional = repository.getDistrictByNameCharacters(name);
        if (optional.isPresent()) return ResponseEntity.ok().body(optional.get());
        return ResponseEntity.ok().body("Not found");
    }


}
