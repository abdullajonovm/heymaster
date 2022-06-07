package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.heymasterapp.entity.Role;
import uz.pdp.heymasterapp.entity.enums.RoleEnum;

public interface  RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(RoleEnum client);

//    @Query()
//    Role findByRoleName(RoleEnum roleName);


}
