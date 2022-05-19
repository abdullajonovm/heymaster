package uz.pdp.heymasterapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.heymasterapp.entity.Category;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;

    public ApiResponse getAll() {
        List<Category> all = categoryRepository.findAll();
        return new ApiResponse("mana", true, all);
    }

    public ApiResponse getOne(Integer id) {

        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) return new ApiResponse("Mana", true, optional.get());
        return new ApiResponse("category not found", false);

    }

    public ApiResponse getByChar(String name) {
        List<Category> categories = categoryRepository.getCategoryByNameCharacters(name);
        return new ApiResponse("Ok", true, categories);
    }

    public ApiResponse add(Category category) {
        Optional<Category> optionalCategory = categoryRepository.findByName(category.getName());
        if (optionalCategory.isPresent()) return new ApiResponse("This category already have !", false);

        categoryRepository.save(category);
        return new ApiResponse("Successfully saved", true);
    }

    public ApiResponse edit(Integer id, Category category) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            Category category1 = optional.get();
            categoryRepository.save(category1);
            return new ApiResponse("Edited", true);
        }
        return new ApiResponse("Not found cateory", false);
    }

    public ApiResponse del(Integer id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            categoryRepository.delete(optional.get());
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("Not found cateory", false);

    }
}

