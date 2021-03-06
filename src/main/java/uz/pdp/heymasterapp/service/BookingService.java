package uz.pdp.heymasterapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.pdp.heymasterapp.dto.AcceptDto;
import uz.pdp.heymasterapp.dto.ApiResponse;
import uz.pdp.heymasterapp.dto.SendNotificationDto;
import uz.pdp.heymasterapp.entity.Booking;
import uz.pdp.heymasterapp.entity.Notification;
import uz.pdp.heymasterapp.entity.User;
import uz.pdp.heymasterapp.feignClient.SendNotification;
import uz.pdp.heymasterapp.repository.BookingRepository;
import uz.pdp.heymasterapp.repository.NotificationRepository;
import uz.pdp.heymasterapp.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService{

    @Value("${key.for.Send.Notification}")
    private String Notification_key;
    final UserRepository userRepository;
    final BookingRepository bookingRepository;
    final NotificationRepository notificationRepository;
    final  SendNotification sendNotification;


    SendNotificationDto sendNotificationDto = new SendNotificationDto();

    public ApiResponse add(User user, Long id) {
        Optional<List<User>> allMasterByActiveIsTrue = userRepository.findAllMasterByActiveIsTrue();
        if (allMasterByActiveIsTrue.isPresent()) {
            for (User user1 : allMasterByActiveIsTrue.get()) {
                if (user1.getId() == id) {
                    Booking booking = new Booking();
                    booking.setFrom(user);
                    booking.setToWhom(user1);
                    booking.setCreatedBy(user.getId());
                    Optional<User> optionalUser = userRepository.findById(id);
                    if (!optionalUser.isPresent()) return new ApiResponse("User not found", false);

                    Notification notification = new Notification();

                    notification.setBody("Sizga  " +user.getFullName() + " tomonidan  buyurtma qoldirdi ");

                    sendNotificationDto.setNotification(notification);
                    sendNotificationDto.setRegistration_ids(Collections.singletonList(optionalUser.get()
                            .getDevice().getDeviceId()));
                    notification.setToWhom(user1);
                    notification.setCreatedBy(user.getId());

                    try {
                       sendNotification.setSendNotification(sendNotificationDto, "key=" + Notification_key);
                        notificationRepository.save(notification);
                    } catch (Exception e) {
                        throw new RuntimeException("notification not send!");
                    }
                    bookingRepository.save(booking);
                    return new ApiResponse("Booking send", true);
                }
            }
        }
        return new ApiResponse("Master not found", false);
    }

    public ApiResponse accept(AcceptDto dto, User user) {
        for (Booking booking : bookingRepository.findByToWhom(user)) {

            if (booking.getId() == dto.getId()) {
                booking.setAccepted(dto.getStatus());
                bookingRepository.save(booking);
                Optional<User> optionalUser = userRepository.findById(booking.getCreatedBy());
                if (!optionalUser.isPresent()) return new ApiResponse("User not found", false);
                Notification notification = new Notification();

                if (dto.getStatus()){
                    notification.setBody("Sizning buyurtmangiz usta " + user.getFullName() +
                            " tomonidan qabul qilindi");
                    user.setBusy(true);
                    userRepository.save(user);
                }
                else {
                   // booking.s
                    notification.setBody("Sizning buyurtmangiz usta  " + user.getFullName() +
                            " tomonidan  rad etildi ");
                }
                sendNotificationDto.setNotification(notification);
                sendNotificationDto.setRegistration_ids(Collections.singletonList(optionalUser.get()
                        .getDevice().getDeviceId()));
                notification.setToWhom(optionalUser.get());
                notification.setCreatedBy(user.getId());

                try {
                    sendNotification.setSendNotification(sendNotificationDto, "key=" + Notification_key);
                    notificationRepository.save(notification);
                } catch (Exception e) {
                    throw new RuntimeException("notification not send!");
                }
                return new ApiResponse("Booking Accepted", true);
            }
        }
        return new ApiResponse("Booking not found", false);
    }


    public ApiResponse active(User user) {
        List<Booking> bookingList = bookingRepository.findByToWhomAndAcceptedTrueAndIsFinishedFalse(user.getId());
        return new ApiResponse("Active list",true,bookingList);
    }

    public ApiResponse history(User user) {
        List<Booking> history = bookingRepository.findByToWhomAndAcceptedTrueAndIsFinishedTrue(user.getId());
        return new ApiResponse("History",true,history);
    }

    public ApiResponse finish(User user, Long id) {
        List<Booking> list = bookingRepository.findByToWhomAndAcceptedTrueAndIsFinishedFalse(user.getId());
        for (Booking booking : list) {
            if (booking.getId()==id){
                booking.setIsFinished(true);
                user.setBusy(false);
                userRepository.save(user);
                Optional<User> client = userRepository.findById(booking.getCreatedBy());
                bookingRepository.save(booking);
                Notification notification = new Notification();
                notification.setBody("Sizning buyurtmangiz usta " + user.getFullName() + " tomonidan tugatildi");
                sendNotificationDto.setNotification(notification);
                sendNotificationDto.setRegistration_ids(Collections.
                        singletonList(client.get().getDevice().getDeviceId()));
                notification.setToWhom(client.get());
                notification.setCreatedBy(user.getId());
                try {
                    sendNotification.setSendNotification(sendNotificationDto, "key=" + Notification_key);
                    notificationRepository.save(notification);
                } catch (Exception e) {
                    throw new RuntimeException("notification not send!");
                }
                return new ApiResponse("Ish yakunlandi ",true);
            }
        }
        return new ApiResponse("Buyurtma topilmadi ",false);
    }

    public ApiResponse clientBookings(User user) {
        List<Booking> bookings = bookingRepository.findByCreatedBy(user.getId());
        return new ApiResponse("OK",true,bookings);
    }

    public ApiResponse getById(User user, Long id) {
        switch (user.getRoles().getRoleName()){
            case SUPER_ADMIN: {
                Optional<Booking> optional = bookingRepository.findById(id);
                if (optional.isPresent()) return new ApiResponse("ok",true,optional.get());
            }
            case CLIENT:{
                List<Booking> bookingList = bookingRepository.findByCreatedBy(user.getId());
                for (Booking booking : bookingList) {
                    if (booking.getId().equals(id)) {
                        return new ApiResponse("ok",true,booking);
                    }
                }
            }
            case MASTER:{
                List<Booking> bookingList = bookingRepository.findByToWhom(user);
                for (Booking booking : bookingList) {
                    if (booking.getId().equals(id)) {
                        return new ApiResponse("ok",true,booking);
                    }
                }
            }
        }
        return new ApiResponse("Buyurtma topilmadi",false);
    }


    public ApiResponse clientHistory(User user) {
        List<Booking> bookings = bookingRepository.findByCreatedByAndIsFinishedTrueAndAcceptedTrue(user.getId());
        return new ApiResponse("Booking list",true,bookings);

    }

    public ApiResponse masterBooking(User user) {
        List<Booking> bookingList = bookingRepository.findByToWhom(user);
        return  new ApiResponse("master booking list",true,bookingList);
    }

//    public ApiResponse delete(User user) {
//        bookingRepository.deleteAllByToWhom(user);
//        return new ApiResponse("Delete ",true);
//    }
//
//    public ApiResponse deleteClientBooking(User user) {
//
//        bookingRepository.deleteAllByCreatedBy(user.getId());
//        return new ApiResponse("Delete",true);
//    }
}
