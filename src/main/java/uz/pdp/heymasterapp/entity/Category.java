package uz.pdp.heymasterapp.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "category")
@EntityListeners(AuditingEntityListener.class)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false)
    private String name;

    private Boolean isActive=true;

    private String photoUrl;

    public Category(Integer id, String name, Boolean isActive) {
        Id = id;
        this.name = name;
        this.isActive = isActive;
    }
}
