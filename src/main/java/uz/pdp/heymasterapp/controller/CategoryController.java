package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.heymasterapp.entity.Category;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.service.CategoryService;

@RequestMapping("/api/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    final CategoryService categoryService;

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity getAll() {
        ApiResponse apiResponse = categoryService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('CLIENT','SUPER_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('CLIENT','MASTER','SUPER_ADMIN')")
    @GetMapping("/search/{name}")
    public ResponseEntity searchByChar(@PathVariable String name) {
        ApiResponse apiResponse = categoryService.getByChar(name);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('MASTER','SUPER_ADMIN')")
    @PostMapping("")
    public ResponseEntity add(@RequestBody Category category) {
        ApiResponse apiResponse = categoryService.add(category);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id,@RequestBody Category category){
        ApiResponse apiResponse=categoryService.edit(id,category);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity del(@PathVariable Integer id){
        ApiResponse apiResponse=categoryService.del(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

}
