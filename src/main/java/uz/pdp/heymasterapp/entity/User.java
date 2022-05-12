package uz.pdp.heymasterapp.entity;

import lombok.*;
import uz.pdp.heymasterapp.entity.template.AbsEntity;
import uz.pdp.heymasterapp.entity.enums.RoleEnum;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "users")
public class User extends AbsEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private RoleEnum role;

    private boolean isActive=true;

    private boolean isBusy;

    private Timestamp approximateEndTime;

    private Double salary;


    @OneToMany
    private List<Attachment> attachments;

    @ManyToOne
    private Location location;

    @ManyToMany
    private List<Profession> professionList;
}
