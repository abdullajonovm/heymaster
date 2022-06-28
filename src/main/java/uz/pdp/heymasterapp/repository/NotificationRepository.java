package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.heymasterapp.entity.Notification;
import uz.pdp.heymasterapp.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<List<Notification>>findAllByCreatedBy(Long createdBy);
    Optional<List<Notification>>findAllByToWhom(User toWhom);
}