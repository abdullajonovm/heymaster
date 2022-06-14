package uz.pdp.heymasterapp.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Data
public class RegisterForMasterDto {
    @NotNull
    private  String phoneNumber;
    @NotNull
    private  String fullName;
    
    @NotNull
    private  int experienceYear;

    private String DeviceId;

    private Double salary;

    private Integer regionId;

    private Integer districtId;

    private Boolean gender;

    private Date birthDate;

    private List<Integer>professionIdList;

    private String password;


}
