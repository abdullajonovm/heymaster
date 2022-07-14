package uz.pdp.heymasterapp.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.heymasterapp.entity.*;
import uz.pdp.heymasterapp.entity.enums.RoleEnum;
import uz.pdp.heymasterapp.entity.location.Location;
import uz.pdp.heymasterapp.repository.*;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    final LocationRepository locationRepository;

    final RegionRepository regionRepository;

    final DistrictRepository districtRepository;

    final AttachmentRepository attachmentRepository;

    final AttachmentContentRepository attachmentContentRepository;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {

                Category category = new Category(1, "Quruvchi", true, "https://bugun.uz/wp-content/uploads/article/image/2021/11/04/fb2e120fa47597a8c73bca9426958cba3207e5fc3136.jpg");
                Category category1 = new Category(2, "Mashina ustasi", true, "https://blog.masterappliance.com/wp-content/uploads/2018/03/With-the-right-tools-you-can-fix-a-short-circuit-in-your-car_2472_40147298_0_14122185_500.jpg");
                Category category2 = new Category(3, "Elektrik", true, "https://avatars.mds.yandex.net/get-altay/2714499/2a00000171038491683eb95a6e9075d5e69d/XXL");
                Category category3 = new Category(4, "Ta'lim", true, "https://images.ctfassets.net/p0qf7j048i0q/73DD33EE82624E7FA1605AA27679294E/89436d5435e4c23fefe79c7cf892d5c5/i177404967.jpg");
                Category category4 = new Category(5, "Uy xodimlari", true, "http://ec.europa.eu/social/BlobServlet?mode=displayPicture&photoId=11710");
                Category category5 = new Category(6, "Bog' xodimlari", true, "https://img.freepik.com/free-vector/set-garden-workers-with-garden-tools-equipment_533582-45.jpg?w=2000");


                List<Category> list = new ArrayList<>(Arrays.asList(category, category1, category2, category3, category4, category5));
                categoryRepository.saveAll(list);
                Profession profession = new Profession(1, "Beton kuyuvchi", category, true, "https://www.familyhandyman.com/wp-content/uploads/2020/09/diy-concrete-GettyImages-478979080.jpg");
                Profession profession1 = new Profession(2, "G'isht terivchi", category, true, "https://d3sux4fmh2nu8u.cloudfront.net/Pictures/1024x536/8/1/6/1863816_bricklayers_625023_crop.jpg");
                Profession profession2 = new Profession(3, "Kuzov", category1, true, "https://auto-tech.center/wp-content/uploads/service-garage.jpg");
                Profession profession3 = new Profession(4, "Mator usta", category1, true, "https://img.freepik.com/free-photo/master-car-mechanic-repairs-the-car-engine-at-the-service-station_199620-9056.jpg?w=2000");
                Profession profession4 = new Profession(5, "Elektr montajchisi", category2, true, "https://www.tlc-direct.co.uk/Images/Products/size_3/TRTS4USLASH25.JPG");
                List<Profession> professionList = new ArrayList<>(Arrays.asList(profession, profession1,
                        profession2, profession3, profession4));
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
                Role roleName = role2;
                user.setRoles(roleName);
                Device device = new Device();
                device.setDeviceId(" foNyTMpnSpGThdbNI7xsBH:APA91bFz_mQQrl1w2eEJFIGqk-FNdEHu1p-kHIw_QMBw_" +
                        "Ccjb0GHLY-g4I9hHccxPeIZjqDDd24_2yKPd2WbjOwMEfm9dSY4y6CKS-Qhbv8LNOmfwQf-z7NOvuMiyxMvLhEKQU9U6e47");
                user.setDevice(device);

                Location location = new Location();
                location.setRegion(regionRepository.findById(7).get());
                location.setDistrict(districtRepository.findById(107).get());
                user.setLocation(location);
                userRepository.save(user);

                User user1 = new User();
                user1.setPhoneNumber("+998994216148");
                user1.setRoles(role1);
                user1.setGender(true);
                user1.setFullName("Demo account");
                user1.setBirthDate(new Date(2001 - 10 - 24));
                user1.setGeneratePassword(passwordEncoder.encode("1111"));
                Device device1 = new Device();
                device1.setDeviceId("fbK0lA8ATpu32zWIYYNH5W:APA91bEotIAHnGRuL44e-i7mnUz-wPIrKaVz1DVjIRjAmXXjnQK3TKJ5qUtfOrgyjjmXW68DdDgfVHVuo9FRTokiBg9mRwHpocQA8Qh04LCStVVr0VNWt4mplKpYED6CfuimSoE41933");
                user1.setDevice(device1);

                location = new Location();
                location.setRegion(regionRepository.findById(8).get());
                location.setDistrict(districtRepository.findById(110).get());

                user1.setLocation(location);

                userRepository.save(user1);

                User user2 = new User();
                user2.setPhoneNumber("+998901281199");
                user2.setRoles(role1);
                user2.setGender(true);
                user2.setFullName("Mirzabek");
                user2.setBirthDate(new Date(1996 - 10 - 24));
                user2.setTotalMark(12l);
                user2.setPeopleReitedCount(3l);

                List<Profession> list1 = new ArrayList<>();
                list1.add(profession1);
                list1.add(profession2);
                list1.add(profession3);
                user2.setProfessionList(list1);

                user2.setGeneratePassword(passwordEncoder.encode("1111"));
                Device device2 = new Device();
                device2.setDeviceId("fbK0lA8ATpu32zWIYYNH5W:APA91bEotIAHnGRuL44e-i7mnUz-wPIrKaVz1DVjIRjAmXXjnQK3TKJ5qUtfOrgyjjmXW68DdDgfVHVuo9FRTokiBg9mRwHpocQA8Qh04LCStVVr0VNWt4mplKpYED6CfuimSoE41933");
                user2.setDevice(device2);

                location = new Location();
                location.setRegion(regionRepository.findById(3).get());
                location.setDistrict(districtRepository.findById(51).get());

                user2.setLocation(location);

                userRepository.save(user2);

                User user3 = new User();
                user3.setPhoneNumber("+998950035369");
                user3.setRoles(role1);
                user3.setGender(true);
                user3.setFullName("Muhammadqodir");
                user3.setBirthDate(new Date(2002 - 11 - 10));
                user3.setTotalMark(100l);
                user3.setPeopleReitedCount(20l);
                List<Profession> list2 = new ArrayList<>();
                list2.add(profession);
                list2.add(profession2);
                list2.add(profession3);
                user3.setProfessionList(list2);

                user3.setGeneratePassword(passwordEncoder.encode("1111"));
                Device device3 = new Device();
                device3.setDeviceId("fbK0lA8ATpu32zWIYYNH5W:APA91bEotIAHnGRuL44e-i7mnUz-wPIrKaVz1DVjIRjAmXXjnQK3TKJ5qUtfOrgyjjmXW68DdDgfVHVuo9FRTokiBg9mRwHpocQA8Qh04LCStVVr0VNWt4mplKpYED6CfuimSoE41933");
                user3.setDevice(device3);

                location = new Location();
                location.setRegion(regionRepository.findById(2).get());
                location.setDistrict(districtRepository.findById(42).get());

                user3.setLocation(location);

                userRepository.save(user3);

                User user4 = new User();
                user4.setPhoneNumber("+998915085515");
                user4.setRoles(role);
                user4.setGender(true);
                user4.setFullName("Feruz");
                user4.setBirthDate(new Date(1999 - 9 - 14));
                user4.setGeneratePassword(passwordEncoder.encode("1111"));
                Device device4 = new Device();
                device4.setDeviceId("fbK0lA8ATpu32zWIYYNH5W:APA91bEotIAHnGRuL44e-i7mnUz-wPIrKaVz1DVjIRjAmXXjnQK3TKJ5qUtfOrgyjjmXW68DdDgfVHVuo9FRTokiBg9mRwHpocQA8Qh04LCStVVr0VNWt4mplKpYED6CfuimSoE41933");
                user4.setDevice(device4);

                location = new Location();
                location.setRegion(regionRepository.findById(10).get());
                location.setDistrict(districtRepository.findById(146).get());

                user4.setLocation(location);

                userRepository.save(user4);

                User user5 = new User();
                user5.setPhoneNumber("+998908151306");
                user5.setRoles(role);
                user5.setGender(true);
                user5.setFullName("Shaxriyor");
                user5.setBirthDate(new Date(2001 - 8 - 10));
                user5.setGeneratePassword(passwordEncoder.encode("1111"));
                Device device5 = new Device();
                device5.setDeviceId("fbK0lA8ATpu32zWIYYNH5W:APA91bEotIAHnGRuL44e-i7mnUz-wPIrKaVz1DVjIRjAmXXjnQK3TKJ5qUtfOrgyjjmXW68DdDgfVHVuo9FRTokiBg9mRwHpocQA8Qh04LCStVVr0VNWt4mplKpYED6CfuimSoE41933");
                user5.setDevice(device5);

                location = new Location();
                location.setRegion(regionRepository.findById(13).get());
                location.setDistrict(districtRepository.findById(194).get());

                user5.setLocation(location);

                userRepository.save(user5);

                User user6 = new User();
                user6.setPhoneNumber("+998978139363");
                user6.setRoles(role1);
                user6.setGender(true);
                user6.setFullName("G`ayratjon");
                user6.setBirthDate(new Date(1995 - 8 - 5));
                user6.setTotalMark(30l);
                user6.setPeopleReitedCount(7l);

                List<Profession> list3 = new ArrayList<>();
                list3.add(profession1);
                list3.add(profession2);
                list3.add(profession);
                user6.setProfessionList(list3);

                user6.setGeneratePassword(passwordEncoder.encode("1111"));
                Device device6 = new Device();
                device6.setDeviceId("fbK0lA8ATpu32zWIYYNH5W:APA91bEotIAHnGRuL44e-i7mnUz-wPIrKaVz1DVjIRjAmXXjnQK3TKJ5qUtfOrgyjjmXW68DdDgfVHVuo9FRTokiBg9mRwHpocQA8Qh04LCStVVr0VNWt4mplKpYED6CfuimSoE41933");
                user6.setDevice(device6);

                location = new Location();
                location.setRegion(regionRepository.findById(12).get());
                location.setDistrict(districtRepository.findById(185).get());

                user6.setLocation(location);

                userRepository.save(user6);

                Advertising advertising = new Advertising();
                advertising.setBody("Mexmash shurflari");
                advertising.setDiscount("mavjud emas");
                advertising.setTitle("Eng sifatlisi bizda");
                advertisingRepository.save(advertising);

                Advertising advertising1 = new Advertising();
                advertising1.setBody("imzo eshik-romlari");
                advertising1.setDiscount("mavjud emas");
                advertising1.setTitle("hayot   yanada yorqinroq");
                advertisingRepository.save(advertising1);

                Advertising advertising2 = new Advertising();
                advertising2.setBody("Cofelletto ");
                advertising2.setDiscount("mavjud emas");
                advertising2.setTitle("kun biz bilan boshlanadi");
                advertisingRepository.save(advertising2);

                Advertising advertising3 = new Advertising();
                advertising3.setBody("Xon-saroy ");
                advertising3.setDiscount("mavjud emas");
                advertising3.setTitle("Biz bilan xonlardek his qiling");
                advertisingRepository.save(advertising3);
            }
        }
    }

