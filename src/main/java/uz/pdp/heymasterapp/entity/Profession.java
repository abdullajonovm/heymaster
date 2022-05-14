package uz.pdp.heymasterapp.entity;

import lombok.*;
import uz.pdp.heymasterapp.entity.template.AbsEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "profession")
public class

Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(nullable = false)
    private String name;
}
