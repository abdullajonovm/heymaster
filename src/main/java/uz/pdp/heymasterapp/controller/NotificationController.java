package uz.pdp.heymasterapp.controller;

import com.google.protobuf.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.entity.Notification;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.repository.NotificationRepository;
import uz.pdp.heymasterapp.security.CurrentUser;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    final NotificationRepository repository;
    ApiResponse apiResponse=new ApiResponse();

    @PreAuthorize("hasAnyAuthority('CLIENT','MASTER')")
    @GetMapping("/all")
    public ResponseEntity getAllClient(@CurrentUser User user){
        Optional<List<Notification>> optionalList = repository.
                findAllByToWhomAndToWhom_Roles_Id(user,user.getRoles().getId());
        if (optionalList.isPresent()){
            apiResponse.setSuccess(true);
            apiResponse.setObject(optionalList.get());
            apiResponse.setMessage("ok");
            return ResponseEntity.ok(apiResponse);
        }
        apiResponse.setSuccess(false);

        apiResponse.setMessage("not notification ");
        return ResponseEntity.ok(apiResponse);
    }


}
