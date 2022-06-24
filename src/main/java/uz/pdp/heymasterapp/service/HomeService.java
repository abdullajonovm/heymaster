package uz.pdp.heymasterapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.heymasterapp.dto.HomPageDto;
import uz.pdp.heymasterapp.entity.Advertising;
import uz.pdp.heymasterapp.entity.Category;
import uz.pdp.heymasterapp.entity.Profession;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.repository.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HomeService {

    // reklamalar,categorylar listi, top kasblar, top masterlar
    final AdvertisingRepository advertisingRepository;
    final UserRepository userRepository;
    final CategoryRepository categoryRepository;
    final ProfessionRepository professionRepository;
    final RateRepository rateRepository;
    final RoleRepository roleRepository;


    public HomPageDto getHomeInfo() {
        HomPageDto homPageDto = new HomPageDto();

        //  activ bo'lgan barcha reklamalar ro'yxati
        List<Advertising> allActiveAdvertising = advertisingRepository.findAllByIsActiveTrue();
        homPageDto.setAdvertisingList(allActiveAdvertising);

        // active bo'lgan barcha categorylar ro'yxati
        List<Category> allActiveCategory = categoryRepository.findAllByIsActiveTrue();
        homPageDto.setCategoryList(allActiveCategory);

        LinkedHashSet<Profession> professions = new LinkedHashSet<>();

//        List<Profession> topProfessions = new ArrayList<>();
        List<User> topMasters = userRepository.topMasters();

        for (User user : topMasters) {
            professions.addAll(user.getProfessionList());
        }

        // top professionlar
        homPageDto.setTopProfessionList(professions);

        // top masterlar
        homPageDto.setTopMastersList(topMasters);

        return homPageDto;
    }

}
