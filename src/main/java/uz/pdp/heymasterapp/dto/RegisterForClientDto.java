package uz.pdp.heymasterapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import uz.pdp.heymasterapp.entity.Attachment;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class RegisterForClientDto {

    @NotNull
    private  String phoneNumber;
    @NotNull
    private  String fullName;

    private Date date;

    private Boolean gender;

//   private Integer attachmentId;

    public RegisterForClientDto(String phoneNumber, String fullName) {
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
    }
}
