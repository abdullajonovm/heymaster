package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.heymasterapp.entity.Profession;
import uz.pdp.heymasterapp.entity.Rate;
import uz.pdp.heymasterapp.entity.Role;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.entity.enums.RoleEnum;

import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate,Long> {

    Optional<List<Rate>>findAllByToWhom(User toWhom);
}
