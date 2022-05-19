package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.heymasterapp.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByPhoneNumber(String email);



    Optional<User> findByPhoneNumber(String email);

}
