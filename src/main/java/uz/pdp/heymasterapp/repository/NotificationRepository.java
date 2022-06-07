package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.heymasterapp.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}