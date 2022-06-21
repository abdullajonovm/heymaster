package uz.pdp.heymasterapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentDto {
    private String fileOriginalName;//pdp.jpg

    private boolean isProfilePhoto;

    private long size;//1024000

    private String contentType;//image/png

    //bu file systemga saqlaganda kk boladi
    private String name;//papkani ichidan topish uchun

    private byte[] asosiyContent;//asosiy content

}
