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
@Entity
public class Rate extends AbsEntity {

    @ManyToOne
    private User fromWhom;
    @ManyToOne
    private User toWhom;

    private double rating;

    private String feedback;

}
