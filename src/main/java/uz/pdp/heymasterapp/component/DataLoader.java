package uz.pdp.heymasterapp.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.heymasterapp.entity.Category;
import uz.pdp.heymasterapp.entity.Profession;
import uz.pdp.heymasterapp.repository.CategoryRepository;
import uz.pdp.heymasterapp.repository.ProfessionRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    final CategoryRepository categoryRepository;
    final ProfessionRepository professionRepository;


    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always") && ddl.equals("create")) {
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
        }
    }
}
