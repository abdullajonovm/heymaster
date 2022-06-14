package uz.pdp.heymasterapp.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.heymasterapp.entity.*;
import uz.pdp.heymasterapp.entity.enums.RoleEnum;
import uz.pdp.heymasterapp.repository.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String mode;


    final CategoryRepository categoryRepository;
    final ProfessionRepository professionRepository;

    final PasswordEncoder passwordEncoder;
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final AdvertisingRepository advertisingRepository;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always") ) {
            Category category = new Category(1,"Quruvchi",true);
            Category category1 = new Category(2,"Mashina ustasi",true);
            Category category2 = new Category(3,"Elektrik",true);
            List<Category>list=new ArrayList<>(Arrays.asList(category,category1,category2));
            categoryRepository.saveAll(list);
            Profession profession = new Profession(1,"Beton kuyuvchi",category,true);
            Profession profession1 = new Profession(2,"G'isht terivchi",category,true);
            Profession profession2 = new Profession(3,"Kuzov",category1,true);
            Profession profession3 = new Profession(4,"Mator usta",category1,true);
            Profession profession4 = new Profession(5,"Montajchi",category2,true);
            List<Profession>professionList=new ArrayList<>(Arrays.asList(profession,profession1,
                    profession2,profession3,profession4));
            professionRepository.saveAll(professionList);

            Role role = new Role();
            role.setRoleName(RoleEnum.CLIENT);
            roleRepository.save(role);
            Role role1 = new Role();
            role1.setRoleName(RoleEnum.MASTER);
            roleRepository.save(role1);
            Role role2 = new Role();
            role2.setRoleName(RoleEnum.SUPER_ADMIN);
            roleRepository.save(role2);

            User user = new User();
            user.setPhoneNumber("+998943234311");
            user.setFullName("Azizbek Abdulaxatov");
            Role roleName = roleRepository.findByRoleName(RoleEnum.SUPER_ADMIN);
            user.setRoles(roleName);
            Device device = new Device();
            device.setDeviceId("fbK0lA8ATpu32zWIYYNH5W:APA91bEotIAHnGRuL44e-i7mnUz-wPIrKaVz1DVjIRjAmXXjnQK3TKJ5qUtfOrgyjjmXW68DdDgfVHVuo9FRTokiBg9mRwHpocQA8Qh04LCStVVr0VNWt4mplKpYED6CfuimSoE41933");
            user.setDevice(device);
            userRepository.save(user);

            User user1 = new User();
            user1.setPhoneNumber("+998123456789");
            user1.setRoles(roleRepository.findByRoleName(RoleEnum.CLIENT));
            user1.setGender(true);
            user1.setFullName("client ");
            user1.setBirthDate(new Date(2001-10-24));
            user1.setGeneratePassword(passwordEncoder.encode("1111"));
            Device device1 = new Device();
            device1.setDeviceId("fbK0lA8ATpu32zWIYYNH5W:APA91bEotIAHnGRuL44e-i7mnUz-wPIrKaVz1DVjIRjAmXXjnQK3TKJ5qUtfOrgyjjmXW68DdDgfVHVuo9FRTokiBg9mRwHpocQA8Qh04LCStVVr0VNWt4mplKpYED6CfuimSoE41933");
            user1.setDevice(device1);
            userRepository.save(user1);

            Advertising advertising = new Advertising();
            advertising.setBody("Mexmash shurflari");
            advertising.setDiscount("mavjud emas");
            advertising.setTitle("Eng sifatlisi bizda");
            advertisingRepository.save(advertising);
        }
    }
}
