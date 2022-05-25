package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.heymasterapp.entity.Profession;
import uz.pdp.heymasterapp.entity.Rate;
import uz.pdp.heymasterapp.entity.Role;
import uz.pdp.heymasterapp.entity.enums.RoleEnum;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate,Long> {

}
