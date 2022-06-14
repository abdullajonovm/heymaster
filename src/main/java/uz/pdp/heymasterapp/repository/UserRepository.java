package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.heymasterapp.entity.Attachment;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.entity.location.District;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByPhoneNumber(String number);

    Attachment findByProfilePhoto(Attachment profilePhoto);
    @Query(value = "select * from users u join users_roles ur on u.id = ur.users_id where u.isActive = true " +
            "and roles_id=1 ", nativeQuery = true)
    Optional<List<User>>findAllClientByActiveIsTrue();
    @Query(value = "select * from users u where roles_id=2 and u.is_active=true", nativeQuery = true)
    Optional<List<User>>findAllMasterByActiveIsTrue();


    @Query(value = "select * from users u where u.total_mark is not null  and u.is_active = true order by total_mark desc limit 3", nativeQuery = true)
    Set<User> topMasters();

    Optional<User> findByPhoneNumber(String phoneNumber);

    @Query(value = "select * from users u join users_roles ur on u.id = ur.users_id where roles_id=1",nativeQuery = true)
    Optional<List<User>> getAllClient();
    @Query(value = "select * from users u join users_roles ur on u.id = ur.users_id where roles_id=2",nativeQuery = true)
    Optional<List<User>> getAllMaster();

    @Query(value = "select c from users c join users_roles ur on c.id=ur.users_id " +
            " where c.fullName LIKE %:name% and roles_id=2 ",nativeQuery = true)
    Optional<List<District>> getMasterByFullName(@Param("name") String name);
    @Query(value = "select c from users c join users_roles ur on c.id=ur.users_id " +
            " where c.fullName LIKE %:name% and roles_id=1 ",nativeQuery = true)
    Optional<List<District>> getClientByFullName(@Param("name") String name);
}

