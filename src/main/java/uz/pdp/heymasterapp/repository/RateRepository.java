package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.heymasterapp.entity.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate,Long> {

}
