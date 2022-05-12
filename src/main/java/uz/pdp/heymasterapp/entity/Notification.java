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
@Entity(name = "notification")
public class Notification extends AbsEntity {

    private String text;

    @ManyToOne
    private User toWhom;


}
