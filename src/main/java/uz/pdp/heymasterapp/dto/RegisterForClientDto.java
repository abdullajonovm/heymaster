package uz.pdp.heymasterapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.heymasterapp.entity.Device;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForClientDto {

//    @Pattern(regexp = "/^[+][998]{3}\\d{9}",message = "+998901234567  korinishida kiritih kerak")
    private  String phoneNumber;
    @NotNull
    private  String fullName;
    private String password;

    private Boolean gender;

    private Date Date;

    private String deviceId ;

    private String deviceLan;

//   private Integer attachmentId;

    public RegisterForClientDto(String phoneNumber, String fullName) {
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
    }
}
