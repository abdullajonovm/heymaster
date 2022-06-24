package uz.pdp.heymasterapp.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.heymasterapp.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Booking extends AbsEntity{

    @ManyToOne
    private User toWhom;

    private boolean isAccepted=false;

    private Boolean isFinished=false;

    @ManyToOne
    private User from;
}
