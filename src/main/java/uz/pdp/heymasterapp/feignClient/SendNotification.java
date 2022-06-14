package uz.pdp.heymasterapp.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import uz.pdp.heymasterapp.dto.SendNotificationDto;
@FeignClient(value = "aaa",url = "https://fcm.googleapis.com/fcm/send")
public interface SendNotification {
    @PostMapping()
    void setSendNotification(@RequestBody SendNotificationDto sendNotificationDto,
                                           @RequestHeader("Authorization") String key) ;
}

