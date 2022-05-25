package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.heymasterapp.entity.Attachment;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {


  // Attachment findByProfilePhoto(boolean profilePhoto);

}
