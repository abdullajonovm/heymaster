package uz.pdp.heymasterapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.heymasterapp.entity.Notification;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SendNotificationDto {
    private Notification notification;

    private List<String> registration_ids;

}
