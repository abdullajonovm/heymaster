package uz.pdp.heymasterapp.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.heymasterapp.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "notification")
@EntityListeners(AuditingEntityListener.class)
public class Notification extends AbsEntity {

    private String title="Hey Master 👨‍🔧👩‍🔧";

    private String body;

    @ManyToOne
    private User toWhom;

}
