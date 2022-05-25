package uz.pdp.heymasterapp.entity.template;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import uz.pdp.heymasterapp.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data
public abstract class AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Timestamp createAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updateAt;

    @JoinColumn(updatable = false)
    @CreatedBy
//    @ManyToOne(fetch = FetchType.LAZY)
    private Long createdBy;

    @LastModifiedBy
//    @ManyToOne(fetch = FetchType.LAZY)
    private Long updatedBy;



}
