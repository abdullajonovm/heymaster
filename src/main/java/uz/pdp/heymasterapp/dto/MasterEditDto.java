package uz.pdp.heymasterapp.dto;

import lombok.Data;

import java.util.List;
@Data
public class MasterEditDto {
    private String fullName;
    private Integer regionId;
    private Integer districtId;
    private Integer experienceYear;
    private List<Integer> professionList;
}
