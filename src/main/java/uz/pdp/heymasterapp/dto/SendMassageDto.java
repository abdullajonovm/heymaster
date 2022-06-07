package uz.pdp.heymasterapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SendMassageDto {
    private String recipients;
    private String originator="Hey Master ";
    private String body="Welcome to Hey master ";
}
