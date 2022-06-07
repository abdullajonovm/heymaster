package uz.pdp.heymasterapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.dto.RateDto;
import uz.pdp.heymasterapp.entity.Rate;
import uz.pdp.heymasterapp.entity.Role;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.entity.enums.RoleEnum;
import uz.pdp.heymasterapp.repository.RateRepository;
import uz.pdp.heymasterapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateService {

    final RateRepository rateRepository;
    final UserRepository userRepository;


    public ApiResponse addRate(RateDto rateDto) {
        Optional<User> byId = userRepository.findById(rateDto.getToWhomId());
        if (!byId.isPresent()) {
            return new ApiResponse("To Whom id xato berilgan. " + rateDto.getToWhomId() + " bunday id dagi user mavjud emas", false);
        }
        User user = byId.get();
        Role role = user.getRoles();
            if (role.getRoleName().name().equals(RoleEnum.MASTER.name())) {

                Rate rate = new Rate();
                rate.setRating(rateDto.getRety());
                rate.setFeedback(rateDto.getFeedback());
                rate.setToWhom(user);
//                rate.setCreatedBy(userRepository.findById(2l).get());
                rateRepository.save(rate);

                //userga rate bergandan shu userni totalMarkiga rateDtoda kelgan reyting balni qoshib qoydik
                Long totalMark = user.getTotalMark();
                totalMark += rateDto.getRety();

                user.setTotalMark(totalMark);
                Long rateCount = user.getPeopleReitedCount() + 1;
                user.setPeopleReitedCount(rateCount);
                userRepository.save(user);
//                System.out.println(user);
//                System.out.println(rate);

                return new ApiResponse("Rate succesfully added", true);
            }

        return new ApiResponse("Bu user master emas backendchilar xato qilyabmiz", false);
    }

    public ApiResponse getAll() {
        List<Rate> all = rateRepository.findAll();
        return new ApiResponse("Mana",true, all);
    }

    public ApiResponse getId(Long id) {
        Optional<Rate> byId = rateRepository.findById(id);
        return byId.map(rate -> new ApiResponse("Mana", true, rate)).orElseGet(() ->
                new ApiResponse("Bunday id dagi rate mavjud emas", false));
    }
}
