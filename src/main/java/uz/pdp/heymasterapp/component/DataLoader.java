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
            device.setDeviceId("fIEcLbSDQtOsXSFNubv3nP:APA91bFXlvAtZ_cqPlL8bOxNbcejf_T_1jVEZBaRCShb6IEPLntl6MFrJHhwOZsUee9ShHJniXbczH-lZcMdlATbBnT6pRFtRcFGQYtBAvKpEktWRuA-b2yuP40ZN_hsCKIZ-Httu43S");
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

            User user2 = new User();
            user2.setPhoneNumber("+998901281199");
            user2.setRoles(roleRepository.findByRoleName(RoleEnum.MASTER));
            user2.setGender(true);
            user2.setFullName("Mirzabek");
            user2.setBirthDate(new Date(1996-10-24));
            user2.setTotalMark(12l);

            List<Profession> list1 = new ArrayList<>();
            list1.add(profession1);
            list1.add(profession2);
            list1.add(profession3);
            user2.setProfessionList(list1);

            user2.setGeneratePassword(passwordEncoder.encode("1111"));
            Device device2 = new Device();
            device2.setDeviceId("fbK0lA8ATpu32zWIYYNH5W:APA91bEotIAHnGRuL44e-i7mnUz-wPIrKaVz1DVjIRjAmXXjnQK3TKJ5qUtfOrgyjjmXW68DdDgfVHVuo9FRTokiBg9mRwHpocQA8Qh04LCStVVr0VNWt4mplKpYED6CfuimSoE41933");
            user2.setDevice(device2);
            userRepository.save(user2);

            User user3 = new User();
            user3.setPhoneNumber("+998990035369");
            user3.setRoles(roleRepository.findByRoleName(RoleEnum.MASTER));
            user3.setGender(true);
            user3.setFullName("Muhammadqodir");
            user3.setBirthDate(new Date(2002-11-10));
            user3.setTotalMark(100l);

            List<Profession> list2 = new ArrayList<>();
            list2.add(profession);
            list2.add(profession2);
            list2.add(profession3);
            user2.setProfessionList(list2);

            user3.setGeneratePassword(passwordEncoder.encode("1111"));
            Device device3 = new Device();
            device3.setDeviceId("fbK0lA8ATpu32zWIYYNH5W:APA91bEotIAHnGRuL44e-i7mnUz-wPIrKaVz1DVjIRjAmXXjnQK3TKJ5qUtfOrgyjjmXW68DdDgfVHVuo9FRTokiBg9mRwHpocQA8Qh04LCStVVr0VNWt4mplKpYED6CfuimSoE41933");
            user3.setDevice(device3);
            userRepository.save(user3);

            User user4 = new User();
            user4.setPhoneNumber("+998915085515");
            user4.setRoles(roleRepository.findByRoleName(RoleEnum.CLIENT));
            user4.setGender(true);
            user4.setFullName("Feruz");
            user4.setBirthDate(new Date(1999-9-14));
            user4.setGeneratePassword(passwordEncoder.encode("1111"));
            Device device4 = new Device();
            device4.setDeviceId("fbK0lA8ATpu32zWIYYNH5W:APA91bEotIAHnGRuL44e-i7mnUz-wPIrKaVz1DVjIRjAmXXjnQK3TKJ5qUtfOrgyjjmXW68DdDgfVHVuo9FRTokiBg9mRwHpocQA8Qh04LCStVVr0VNWt4mplKpYED6CfuimSoE41933");
            user4.setDevice(device4);
            userRepository.save(user4);

            User user5 = new User();
            user5.setPhoneNumber("+998911122767");
            user5.setRoles(roleRepository.findByRoleName(RoleEnum.CLIENT));
            user5.setGender(true);
            user5.setFullName("Shaxriyor");
            user5.setBirthDate(new Date(2001-8-10));
            user5.setGeneratePassword(passwordEncoder.encode("1111"));
            Device device5 = new Device();
            device5.setDeviceId("fbK0lA8ATpu32zWIYYNH5W:APA91bEotIAHnGRuL44e-i7mnUz-wPIrKaVz1DVjIRjAmXXjnQK3TKJ5qUtfOrgyjjmXW68DdDgfVHVuo9FRTokiBg9mRwHpocQA8Qh04LCStVVr0VNWt4mplKpYED6CfuimSoE41933");
            user5.setDevice(device5);
            userRepository.save(user5);

            User user6 = new User();
            user6.setPhoneNumber("+998978139363");
            user6.setRoles(roleRepository.findByRoleName(RoleEnum.MASTER));
            user6.setGender(true);
            user6.setFullName("G`ayratjon");
            user6.setBirthDate(new Date(1995-8-5));
            user6.setTotalMark(30l);

            List<Profession> list3 = new ArrayList<>();
            list3.add(profession1);
            list3.add(profession2);
            list3.add(profession);
            user2.setProfessionList(list3);

            user6.setGeneratePassword(passwordEncoder.encode("1111"));
            Device device6 = new Device();
            device6.setDeviceId("fbK0lA8ATpu32zWIYYNH5W:APA91bEotIAHnGRuL44e-i7mnUz-wPIrKaVz1DVjIRjAmXXjnQK3TKJ5qUtfOrgyjjmXW68DdDgfVHVuo9FRTokiBg9mRwHpocQA8Qh04LCStVVr0VNWt4mplKpYED6CfuimSoE41933");
            user6.setDevice(device6);
            userRepository.save(user6);

            Advertising advertising = new Advertising();
            advertising.setBody("Mexmash shurflari");
            advertising.setDiscount("mavjud emas");
            advertising.setTitle("Eng sifatlisi bizda");
            advertisingRepository.save(advertising);
        }
    }
}
