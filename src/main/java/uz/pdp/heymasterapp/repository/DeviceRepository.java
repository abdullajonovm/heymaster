package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.heymasterapp.entity.Device;
import uz.pdp.heymasterapp.entity.Role;
import uz.pdp.heymasterapp.entity.enums.RoleEnum;

public interface DeviceRepository extends JpaRepository<Device,Integer> {



}
