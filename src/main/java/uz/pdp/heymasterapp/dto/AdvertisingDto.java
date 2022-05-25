package uz.pdp.heymasterapp.dto;

import lombok.Data;

@Data
public class AdvertisingDto {
    private String body;

    private String title;

    private String discount;

    private Long attachmentId;
}
