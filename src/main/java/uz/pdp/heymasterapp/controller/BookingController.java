package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.security.CurrentUser;
import uz.pdp.heymasterapp.service.BookingService;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {
    final BookingService bookingService;

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','MASTER','CLIENT')")
    @PostMapping("/{id}")
    public ResponseEntity add(@CurrentUser User user, @PathVariable Long id) {
        ApiResponse apiResponse = bookingService.add(user, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','MASTER')")
    @GetMapping("/accept")
    public ResponseEntity accept(@PathVariable Long id, @CurrentUser User user) {
        ApiResponse apiResponse = bookingService.accept(id, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','MASTER')")
    @GetMapping("/active")
    public ResponseEntity active(@CurrentUser User user) {
        ApiResponse apiResponse = bookingService.active(user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','MASTER')")
    @GetMapping("/master/history")
    public ResponseEntity history(@CurrentUser User user) {
        ApiResponse apiResponse = bookingService.history(user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }
//    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','MASTER')")
//    @GetMapping("/client/history")
//    public ResponseEntity historyClient(@CurrentUser User user) {
//        ApiResponse apiResponse = bookingService.clientHistory(user);
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
//    }
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','MASTER')")
    @GetMapping("/finish/{id}")
    public ResponseEntity finish(@CurrentUser User user,@PathVariable Long id) {
        ApiResponse apiResponse = bookingService.finish(user,id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','CLIENT')")
    @GetMapping("/client")
    public ResponseEntity clientBooking(@CurrentUser User user) {
        ApiResponse apiResponse = bookingService.clientBookings(user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','MASTER','CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity getById(@CurrentUser User user,@PathVariable Long id) {
        ApiResponse apiResponse = bookingService.getById(user,id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }



}
