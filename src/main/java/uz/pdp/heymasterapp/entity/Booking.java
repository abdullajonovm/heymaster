package uz.pdp.heymasterapp.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.heymasterapp.entity.template.AbsEntity;

import javax.persistence.*;

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

    @OneToOne
    private Notification notification;

    private boolean isAccepted;

    private Boolean isFinished;

}
