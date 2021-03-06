package uz.pdp.heymasterapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.entity.Attachment;
import uz.pdp.heymasterapp.entity.AttachmentContent;
import uz.pdp.heymasterapp.repository.AttachmentContentRepository;
import uz.pdp.heymasterapp.repository.AttachmentRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentService {


    final AttachmentRepository attachmentRepository;

   final AttachmentContentRepository attachmentContentRepository;

    public ApiResponse upload(MultipartHttpServletRequest request) {
        //faqat nomlari
        Iterator<String> fileNames = request.getFileNames();
        //realniy fayllar
        ApiResponse apiResponse = new ApiResponse();

        while (fileNames.hasNext()) {
            List<MultipartFile> files = request.getFiles(fileNames.next());
            for (MultipartFile file : files) {
                Attachment attachment = new Attachment(
                        file.getOriginalFilename(),
                        file.getSize(),
                        file.getContentType());

                Attachment save = attachmentRepository.save(attachment);
                apiResponse.setObject(save.getId());
                apiResponse.setSuccess(true);
                apiResponse.setMessage("mana");
                AttachmentContent attachmentContent = new AttachmentContent();
                attachmentContent.setAttachment(save);
                try {
                    attachmentContent.setAsosiyContent(file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                attachmentContentRepository.save(attachmentContent);
            }
        }
        return apiResponse;
    }

    public ApiResponse delete(Long id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){

            attachmentContentRepository.deleteById(id);

            return new ApiResponse("Rasm o'chirildi",true);
        }
        return new ApiResponse("Rasm topilmadi",false);
    }
}
