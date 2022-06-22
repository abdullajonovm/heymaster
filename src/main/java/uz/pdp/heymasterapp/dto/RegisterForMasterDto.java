package uz.pdp.heymasterapp.dto;


import lombok.Data;
import uz.pdp.heymasterapp.entity.Device;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Data
public class RegisterForMasterDto {
    private  String phoneNumber;

    private  String fullName;
    
    private  int experienceYear;

    private String deviceId;

    private Double salary;

    private Integer regionId;

    private Integer districtId;
    private String deviceLan;

    private Boolean gender;

    private Date birthDate;

    private List<Integer>professionIdList;

    private String password;


}
