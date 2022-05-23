package uz.pdp.heymasterapp.entity;

import lombok.*;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.heymasterapp.entity.location.Location;
import uz.pdp.heymasterapp.entity.template.AbsEntity;
import uz.pdp.heymasterapp.entity.enums.RoleEnum;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "users")
public class User extends AbsEntity implements UserDetails {

    @Column(nullable = false)
    private String fullName;
    private int experienceYear=0;

    @Column(nullable = false)
    private String phoneNumber;

    private String  generatePassword;

    private boolean isActive=true;

    private boolean isBusy;

    private Timestamp approximateEndTime;

    private Double salary;

    @OneToOne
    private Attachment profilePhoto;


    @OneToMany
    private List<Attachment> attachments;

    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;

    @ManyToMany
    private List<Profession> professionList;


    @ManyToMany
    private Set<Role> roles;

    //Quyidagilar UserDetails ning method lari
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }
    @Override
    public String getPassword() {
        return this.getGeneratePassword();
    }

    @Override
    public String getUsername() {
        return this.phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
