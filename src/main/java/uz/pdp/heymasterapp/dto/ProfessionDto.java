package uz.pdp.heymasterapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfessionDto {
    @NotNull(message = "you must write name !")
    private String  name;
    @NotEmpty(message = "you need choose category !")
    private Integer categoryId;
}
