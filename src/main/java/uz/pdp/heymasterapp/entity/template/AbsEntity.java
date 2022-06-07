package uz.pdp.heymasterapp.entity.template;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AbsEntity<U> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(updatable = false)
    @CreatedBy
//    @ManyToOne(fetch = FetchType.LAZY)
    private Long createdBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(updatable = false, nullable = false)
    private Date createDate;

    @LastModifiedBy
//    @ManyToOne(fetch = FetchType.LAZY)
    private Long updatedBy;


    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(nullable = false)
    private Date lastModifiedDate;





}
