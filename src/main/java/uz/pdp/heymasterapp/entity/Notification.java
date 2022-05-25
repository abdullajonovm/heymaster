package uz.pdp.heymasterapp.entity;

import lombok.*;
import uz.pdp.heymasterapp.entity.template.AbsEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "notification")
public class Notification extends AbsEntity {

    private String text;

    private String body;

    @ManyToOne
    private User toWhom;

}
