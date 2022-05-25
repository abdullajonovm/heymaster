package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.heymasterapp.entity.Advertising;

import java.util.List;

public interface AdvertisingRepository extends JpaRepository<Advertising, Long> {
    List<Advertising> findAllByIsActiveTrue();
}