package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;

import uz.pdp.heymasterapp.entity.Notification;
import uz.pdp.heymasterapp.security.CurrentUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.heymasterapp.dto.SendNotificationDto;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.feignClient.SendNotification;

import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class SendNotificationController{
    final SendNotification sendNotification;

    @Value("${key.for.Send.Notification}")
    private String Notification_key;

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','MASTER','CLIENT')")
    @PostMapping("/send")
    public ResponseEntity send(@CurrentUser User user) {
        Notification notification = new Notification();
        notification.setBody("Booking ! ");
        SendNotificationDto sendNotificationDto = new SendNotificationDto();
        sendNotificationDto.setNotification(notification);
        sendNotificationDto.setRegistration_ids(Collections.singletonList(user.getDevice().getDeviceId()));
        try {
            sendNotification.setSendNotification(sendNotificationDto, "key=" + Notification_key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body("ok");
    }


}
