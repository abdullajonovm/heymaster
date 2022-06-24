package uz.pdp.heymasterapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.dto.ProfessionDto;
import uz.pdp.heymasterapp.entity.Category;
import uz.pdp.heymasterapp.entity.Profession;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.repository.CategoryRepository;
import uz.pdp.heymasterapp.repository.ProfessionRepository;
import uz.pdp.heymasterapp.repository.UserRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProfessionService {
    final ProfessionRepository repository;
    final CategoryRepository categoryRepository;
    final UserRepository userRepository;

    public ApiResponse getAll() {
        return new ApiResponse("OK", true, repository.findAll());
    }

    public ApiResponse getOne(Integer id) {
        Optional<Profession> optional = repository.findById(id);
        if (optional.isPresent()) {
            return new ApiResponse("Ok", true, optional.get());
        }
        return new ApiResponse("Not found profession", false);

    }

    public ApiResponse getByChar(String name) {
        List<Category> categoryList = repository.getProfessionByCharacter(name);
        return new ApiResponse("Ok", true, categoryList);
    }

    public ApiResponse add(ProfessionDto professionDto) {
        Optional<Profession> optional = repository.findByName(professionDto.getName());
        if (optional.isPresent()) {
            return new ApiResponse("This profession already exist", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(professionDto.getCategoryId());
        if (optional.isPresent()) {
            Profession profession = new Profession();
            profession.setName(professionDto.getName());
            profession.setCategory(optionalCategory.get());
            profession.setPhotoUrl(professionDto.getPhotoUrl());
            repository.save(profession);
            return new ApiResponse("saved", true);

        }
        return new ApiResponse("Not found this category", false);
    }

    public ApiResponse edit(Integer id, ProfessionDto professionDto) {
        Optional<Profession> optionalProfession = repository.findById(id);
        if (!optionalProfession.isPresent()) return new ApiResponse("Not found this profession",
                false);

        Optional<Category> optionalCategory = categoryRepository.findById(professionDto.getCategoryId());
        if (optionalCategory.isPresent()) {
            Profession profession = optionalProfession.get();
            profession.setName(profession.getName());
            profession.setCategory(optionalCategory.get());
            profession.setPhotoUrl(professionDto.getPhotoUrl());
            repository.save(profession);
            return new ApiResponse("edited !", true);

        }
        return new ApiResponse("Not found this category", false);

    }

    public ApiResponse del(Integer id) {
        Optional<Profession> optional = repository.findById(id);
        if (!optional.isPresent()) return new ApiResponse("not found profession",
                false);
        Profession profession = optional.get();
        profession.setIsActive(false);
        repository.save(profession);
        return new ApiResponse("Deleted", true);
    }

    public ApiResponse getAllActive() {
        List<Profession> professionList = repository.getAllByIsActiveTrue();
        return new ApiResponse("Ok", true, professionList);
    }

    public ApiResponse findMasterByProfessionId(Integer id) {
        Optional<List<User>> allMasterByActiveIsTrue = userRepository.findAllMasterByActiveIsTrue();
        if (!allMasterByActiveIsTrue.isPresent()){
            return new ApiResponse("Umuman usta yoq", false);
        }
        List<User> userList = allMasterByActiveIsTrue.get();
        List<User> findMasterByProfessionId = new ArrayList<>();
        for (User user : userList) {
            for (Profession profession : user.getProfessionList()) {
                if (profession.getId() == id) {
                    findMasterByProfessionId.add(user);
                }
            }
        }

        if (findMasterByProfessionId.isEmpty()) return new ApiResponse("Bu profession ustalari yoq", false);
        return new ApiResponse("Mana", true, findMasterByProfessionId);
    }
}
