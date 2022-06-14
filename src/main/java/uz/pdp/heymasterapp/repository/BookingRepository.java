package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.heymasterapp.entity.Booking;
import uz.pdp.heymasterapp.entity.User;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {


    List<Booking> findByToWhom(User user);

    @Query(value = "select * from orders o where o.to_whom_id=:user and o.is_finished=false and o.is_accepted=true"
            ,nativeQuery = true)
    List<Booking>findByToWhomAndAcceptedTrueAndIsFinishedFalse(Long user);

//    @Query(value = "select * from orders o where o.to_whom_id=:user and o.is_finished=true and o.is_accepted=true"
//            ,nativeQuery = true)
//     List<Booking>findByToWhomAndAcceptedTrueAndIsFinishedTrue(Long toWhom);

     List<Booking>findByCreatedBy(Long createdBy);


}