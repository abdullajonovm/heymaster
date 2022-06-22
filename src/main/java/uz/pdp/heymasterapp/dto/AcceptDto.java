package uz.pdp.heymasterapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcceptDto {
    private Boolean status;
    private Long id;
}
