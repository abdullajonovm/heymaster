package uz.pdp.heymasterapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.heymasterapp.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse2 {
    private String message;
    private boolean success;
    private Object object;
    private User user;

    public ApiResponse2(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}