package uz.pdp.heymasterapp.controller;

import com.google.protobuf.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.heymasterapp.dto.AcceptDto;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.security.CurrentUser;
import uz.pdp.heymasterapp.service.BookingService;

import java.util.Collections;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {
    final BookingService bookingService;

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','CLIENT')")
    @PostMapping("/{id}")
    public ResponseEntity add(@CurrentUser User user, @PathVariable Long id) {
        ApiResponse apiResponse = bookingService.add(user, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','MASTER')")
    @PostMapping("/accept")
    public ResponseEntity accept(@RequestBody AcceptDto dto, @CurrentUser User user) {
        ApiResponse apiResponse = bookingService.accept(dto, user);
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

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','CLIENT')")
    @GetMapping("/client/history")
    public ResponseEntity historyClient(@CurrentUser User user) {
        ApiResponse apiResponse = bookingService.clientHistory(user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','MASTER')")
    @GetMapping("/finish/{id}")
    public ResponseEntity finish(@CurrentUser User user, @PathVariable Long id) {
        ApiResponse apiResponse = bookingService.finish(user, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','CLIENT')")
    @GetMapping("/client")
    public ResponseEntity clientBooking(@CurrentUser User user) {
        ApiResponse apiResponse = bookingService.clientBookings(user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','MASTER')")
    @GetMapping("/master")
    public ResponseEntity masterBooking(@CurrentUser User user) {
        ApiResponse apiResponse = bookingService.masterBooking(user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','MASTER','CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity getById(@CurrentUser User user, @PathVariable Long id) {
        ApiResponse apiResponse = bookingService.getById(user, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

//    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','MASTER')")
//    @DeleteMapping("/master/allDelete")
//    public ResponseEntity deleteAll(@CurrentUser User user) {
//        ApiResponse apiResponse = bookingService.delete(user);
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
//    }
//
//    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','CLIENT')")
//    @DeleteMapping("/client/allDelete")
//    public ResponseEntity deleteAllClientBooking(@CurrentUser User user) {
//        ApiResponse apiResponse = bookingService.deleteClientBooking(user);
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
//    }

//    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','MASTER','CLIENT')")
//    @DeleteMapping("/client/allDelete")
//    public ResponseEntity (@CurrentUser User user) {
//        ApiResponse apiResponse = bookingService.deleteClientBooking(user);
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
//    }


}
