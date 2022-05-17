package uz.pdp.heymasterapp.entity.location;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Region region;

    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;



}
