package uz.pdp.heymasterapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import uz.pdp.heymasterapp.entity.Attachment;

import javax.validation.constraints.NotNull;

@Data
public class RegisterForClientDto {

    @NotNull
    private  String phoneNumber;
    @NotNull
    private  String fullName;

//   private Integer attachmentId;

    public RegisterForClientDto(String phoneNumber, String fullName) {
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
    }
}
