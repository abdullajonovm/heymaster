package uz.pdp.heymasterapp.entity;


import lombok.*;
import uz.pdp.heymasterapp.entity.template.AbsEntity;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "location")
public class Location extends AbsEntity {
    private String region;
    private String city;
    private String street;
    private String homeNumber;

}
