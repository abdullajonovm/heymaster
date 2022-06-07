package uz.pdp.heymasterapp.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import uz.pdp.heymasterapp.dto.SendMassageDto;

@FeignClient(value = "feign",url = "https://rest.messagebird.com/messages")
public interface SendMassage {
    @PostMapping()
    void sendMassages(@RequestBody SendMassageDto sendMassageDto,
                      @RequestHeader ("Authorization") String key );
}

