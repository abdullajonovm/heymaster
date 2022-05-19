package uz.pdp.heymasterapp.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterForClientDto {

    @NotNull
    private  String phoneNumber;
    @NotNull
    private  String fullName;


}
