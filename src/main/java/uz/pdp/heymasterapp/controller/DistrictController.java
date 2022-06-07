package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.heymasterapp.entity.location.District;
import uz.pdp.heymasterapp.repository.DistrictRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/district")
@RequiredArgsConstructor
public class DistrictController {
    final DistrictRepository repository;
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity getAll(){
        List<District> regionList = repository.findAll();
        return ResponseEntity.ok().body(regionList);
    }
    @PreAuthorize("hasAnyAuthority('MASTER','SUPER_ADMIN','CLIENT')")
    @GetMapping("/region/{id}")
    public ResponseEntity getByRegionId(@PathVariable Integer id){
        Optional<District> optional = repository.findByRegionId(id);
        if (!optional.isPresent()) {
            return ResponseEntity.ok().body("District not found");
        }
        return ResponseEntity.ok().body(optional.get());
    }
    @PreAuthorize("hasAnyAuthority('MASTER','SUPER_ADMIN','CLIENT')")
    @GetMapping("/search/{name}")
    public ResponseEntity getDistrictByName(@PathVariable String name){
        Optional<List<District>> optional = repository.getDistrictByNameCharacters(name);
        if (optional.isPresent()) return ResponseEntity.ok().body(optional.get());
        return ResponseEntity.ok().body("Not found");
    }
}
