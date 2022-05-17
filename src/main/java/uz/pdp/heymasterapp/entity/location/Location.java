package uz.pdp.heymasterapp.entity.location;


import lombok.*;
import uz.pdp.heymasterapp.entity.template.AbsEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    private Region region;

    @ManyToOne
    private District district;

    private String street;

    private String homeNumber;

}
