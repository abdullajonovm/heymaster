package uz.pdp.heymasterapp.dto;

import lombok.Data;

import java.util.Map;

@Data
public class NoteDto {
    private String subject;
    private String content;
    private Map<String, String> data;
    private String image;

}
