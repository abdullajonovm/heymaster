package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {

    @PostMapping("/{id}")
    public ResponseEntity booked(@PathVariable Integer id){
        return null;
    }

}
