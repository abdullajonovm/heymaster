package uz.pdp.heymasterapp.entity;

import lombok.*;
import uz.pdp.heymasterapp.entity.template.AbsEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "orders")
public class Order extends AbsEntity{

    @ManyToOne
    private User toWhom;

    @OneToOne
    private Notification notification;

    private boolean isAccepted;

    private Boolean isFinished;

}
