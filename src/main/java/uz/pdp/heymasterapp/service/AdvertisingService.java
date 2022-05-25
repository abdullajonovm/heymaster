package uz.pdp.heymasterapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.heymasterapp.dto.AdvertisingDto;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.entity.Advertising;
import uz.pdp.heymasterapp.entity.Attachment;
import uz.pdp.heymasterapp.repository.AdvertisingRepository;
import uz.pdp.heymasterapp.repository.AttachmentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertisingService {
    final AdvertisingRepository advertisingRepository;
    final AttachmentRepository attachmentRepository;
    public ApiResponse addAdvertising(AdvertisingDto advertisingDto) {
        Advertising advertising = new Advertising();
        Optional<Attachment> byId = attachmentRepository.findById(advertisingDto.getAttachmentId());
        if (!byId.isPresent()){
            return new ApiResponse("Bnday id dagi attachment mavjud emas", false);
        }
        advertising.setAttachment(byId.get());
        advertising.setBody(advertisingDto.getBody());
        advertising.setDiscount(advertisingDto.getDiscount());
        advertising.setTitle(advertisingDto.getTitle());
        advertising.setIsActive(true);
        advertisingRepository.save(advertising);
        return new ApiResponse("Advertising add qilindi", true);
    }
    public ApiResponse edited(Long advertisingId, AdvertisingDto advertisingDto) {
        Optional<Advertising> optionalAdvertising = advertisingRepository.findById(advertisingId);
        if (!optionalAdvertising.isPresent()) return new ApiResponse("Bunday ida dagi advertising mavjud emas", false);
        Advertising advertising = optionalAdvertising.get();
        Optional<Attachment> byId = attachmentRepository.findById(advertisingDto.getAttachmentId());
        if (!byId.isPresent()){
            return new ApiResponse("Bnday id dagi attachment mavjud emas", false);
        }
        advertising.setAttachment(byId.get());
        advertising.setBody(advertisingDto.getBody());
        advertising.setDiscount(advertisingDto.getDiscount());
        advertising.setTitle(advertisingDto.getTitle());
        advertising.setIsActive(true);
        advertisingRepository.save(advertising);
        return new ApiResponse("Advertising edit qilindi", true);
    }


    public ApiResponse deleted(Long id) {
        Optional<Advertising> byId = advertisingRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Id xato kelyabdi etibor bering", false);
        Advertising advertising = byId.get();
        advertising.setIsActive(false);
        advertisingRepository.save(advertising);
        return new ApiResponse("Delet korzinkaga olindi", true);
    }

    public ApiResponse getAll() {
        List<Advertising> all = advertisingRepository.findAll();
        return new ApiResponse("Mana", true, all);
    }

    public ApiResponse getOne(Long id) {
        Optional<Advertising> byId = advertisingRepository.findById(id);
        if (byId.isPresent()){
            return new ApiResponse("Mana", true, byId.get());
        }
        return new ApiResponse("Bunday id dagi advertising yo'q", false);
    }
}
