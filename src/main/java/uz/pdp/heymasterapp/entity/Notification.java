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
@Entity(name = "notification")
@EntityListeners(AuditingEntityListener.class)
public class Notification extends AbsEntity {

    private String text;

    private String body;

    @ManyToOne
    private User toWhom;

}
