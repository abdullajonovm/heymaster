package uz.pdp.heymasterapp.dto;

import lombok.Data;

@Data
public class RateDto {
    private Long toWhomId;
    private Long rating;
    private String feedback;
}
