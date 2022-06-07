package uz.pdp.heymasterapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.heymasterapp.dto.HomPageDto;
import uz.pdp.heymasterapp.entity.Advertising;
import uz.pdp.heymasterapp.entity.Category;
import uz.pdp.heymasterapp.entity.Profession;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.repository.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


        Set<Profession> topProfessions = new HashSet<>();

        //top 10 lini jonatish uchun yangi map ochib oldim
//        Map<User, Double> topMasters = new HashMap<>();
//        Set<User> topMasters = new HashSet<>();
        Set<User> topMasters = userRepository.topMasters();
        for (User user : topMasters) {
            for (Profession profession : user.getProfessionList()) {
                topProfessions.add(profession);
            }
        }
        System.out.println("\n\n\n\n\n\n\n=================================================\n");
        System.out.println(topProfessions);
        // top professionlar
        homPageDto.setTopProfessionList(topProfessions);

        // top masterlar
        homPageDto.setTopMastersList(topMasters);

        return homPageDto;
    }

}
