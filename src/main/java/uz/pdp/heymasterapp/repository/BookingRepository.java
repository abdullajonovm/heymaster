package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.heymasterapp.entity.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}