package uz.pdp.heymasterapp.entity;

import lombok.*;

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

    @ManyToOne
    private Category category;

    private Boolean isActive=true;

    private String professionPhoto;

    public Profession(Integer id, String name, Category category, Boolean isActive) {
        Id = id;
        this.name = name;
        this.category = category;
        this.isActive = isActive;
    }
}
