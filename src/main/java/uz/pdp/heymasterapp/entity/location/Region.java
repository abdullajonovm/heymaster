package uz.pdp.heymasterapp.entity.location;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;



}
