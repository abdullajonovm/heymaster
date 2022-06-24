package uz.pdp.heymasterapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.heymasterapp.entity.Advertising;
import uz.pdp.heymasterapp.entity.Category;
import uz.pdp.heymasterapp.entity.Profession;
import uz.pdp.heymasterapp.entity.User;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomPageDto {
    private List<Advertising> advertisingList;
    private List<Category> categoryList;
    private LinkedHashSet<Profession> topProfessionList;
    private List<User> topMastersList;
}
