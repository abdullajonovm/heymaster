package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.heymasterapp.entity.Attachment;
import uz.pdp.heymasterapp.entity.Profession;
import uz.pdp.heymasterapp.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByPhoneNumber(String number);



    Optional<User> findByPhoneNumber(String number);
    Attachment findByProfilePhoto(Attachment profilePhoto);
    @Query("select u from users u where u.isActive = true")
    Optional<List<User>>findAllByActiveIsTrue();
}
