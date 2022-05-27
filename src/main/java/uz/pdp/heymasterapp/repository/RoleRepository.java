package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.heymasterapp.entity.Role;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.entity.enums.RoleEnum;

import java.util.List;

public interface  RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(RoleEnum client);

//    @Query()
//    Role findByRoleName(RoleEnum roleName);


}
