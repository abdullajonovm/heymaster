package uz.pdp.heymasterapp.controller;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.entity.Attachment;
import uz.pdp.heymasterapp.entity.AttachmentContent;
import uz.pdp.heymasterapp.repository.AttachmentContentRepository;
import uz.pdp.heymasterapp.repository.AttachmentRepository;
import uz.pdp.heymasterapp.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {


    final AttachmentRepository attachmentRepository;

    final AttachmentContentRepository attachmentContentRepository;

    final AttachmentService attachmentService;

//    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','CLIENT','MASTER')")
    @GetMapping("/info")
    public List<Attachment> getInfo() {
        List<Attachment> all = attachmentRepository.findAll();
        return all;
    }
//    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','CLIENT','MASTER')")
    @PostMapping("/upload")
    public String uploadFile(MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();

        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            //file haqida malumot olish uchun
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment = new Attachment();

            attachment.setFileOriginalName(originalFilename);
            attachment.setSize(size);
            attachment.setContentType(contentType);

            Attachment save = attachmentRepository.save(attachment);

            //file ni byte [] saqlaymiz

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setAttachment(save);
            attachmentContent.setAsosiyContent(file.getBytes());

            AttachmentContent save1 = attachmentContentRepository.save(attachmentContent);
            return "Fayl saqlandi. ID si: " + save.getId();
        }
        return "Xatolik";
    }

//    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','CLIENT','MASTER')")
    @GetMapping("/download/{id}")
    public void getFile(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);

        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentId(id);
            if (contentOptional.isPresent()) {
                AttachmentContent attachmentContent = contentOptional.get();
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + attachment.getFileOriginalName());
                response.setContentType(attachment.getContentType());
               FileCopyUtils.copy(attachmentContent.getAsosiyContent(), response.getOutputStream());
            }
        }
    }

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','CLIENT','MASTER')")
    @PostMapping("/uploadFiles")
    public ApiResponse upload(MultipartHttpServletRequest request) {
        return attachmentService.upload(request);
    }
//    private static final String uploadDirectory = "files";
//
//    @PostMapping("/uploadSystem")
//    public String uploadFiletoFileSystem(MultipartHttpServletRequest request) throws IOException {
//        Iterator<String> fileNames = request.getFileNames();
//        MultipartFile file = request.getFile(fileNames.next());
//        if (file != null) {
//            String originalFilename = file.getOriginalFilename();
//
//            Attachment attachment = new Attachment();
//
//            attachment.setFileOriginalName(originalFilename);
//            attachment.setSize(file.getSize());
//            attachment.setContentType(file.getContentType());
//
//            String[] split = originalFilename.split("\\.");
//
//            String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
//
//            attachment.setName(name);
//            attachmentRepository.save(attachment);
//
//            Path path = Paths.get(uploadDirectory +"/"+ name);
//            Files.copy(file.getInputStream(), path);
//            return "File saqlandi . ID si : " + attachment.getId();
//        }
//        return "Saqlanmadi";
//    }

//    @GetMapping("/downloadSystem/{id}")
//    public void getFileToFileSystem(@PathVariable Integer id,HttpServletResponse response) throws IOException {
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
//        if (optionalAttachment.isPresent()){
//            Attachment attachment = optionalAttachment.get();
//            response.setHeader("Content-Disposition",
//                    "attachment; filename=" + attachment.getFileOriginalName());
//            response.setContentType(attachment.getContentType());
//
//            FileInputStream fileInputStream=new FileInputStream(uploadDirectory+"/"+attachment.getName());
//
//            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
//        }
//    }


}
