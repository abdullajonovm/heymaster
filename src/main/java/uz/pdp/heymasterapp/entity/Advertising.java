package uz.pdp.heymasterapp.entity;

import lombok.*;
import uz.pdp.heymasterapp.entity.template.AbsEntity;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Advertising extends AbsEntity {

    private String body;

    private String title;

    private String discount;

    @OneToOne
    private Attachment attachment;
}
