package uz.pdp.heymasterapp.entity;

import lombok.*;
import uz.pdp.heymasterapp.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "orders")
public class Order extends AbsEntity {

    @ManyToOne
    private User fromWhom;
    @ManyToOne
    private User toWhom;

    @OneToOne
    private Notification notification;

    private Boolean isFinished;

}
