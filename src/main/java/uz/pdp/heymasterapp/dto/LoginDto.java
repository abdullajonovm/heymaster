package uz.pdp.heymasterapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto {
    @Pattern(regexp = "/^[+][998]{3}\\d{7}",message = "+998901234567  korinishida kiritih kerak")
    private String phoneNumber;
    @NotBlank(message = "Enter password please ")
    private String password;
}
