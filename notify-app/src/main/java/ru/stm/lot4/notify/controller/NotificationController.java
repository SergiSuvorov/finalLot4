package ru.stm.lot4.notify.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stm.lot4.db.model.PushNotification;
import ru.stm.lot4.dto.model.PushNotificationRequest;
import ru.stm.lot4.notify.service.NotificationService;

@RestController
@RequestMapping("/receiver/pushNotification")
@RequiredArgsConstructor
@Validated
public class NotificationController {

    private final NotificationService kafkaService;

    @PostMapping("/create")
    public ResponseEntity<PushNotification> createNotification(
            @Valid @RequestBody PushNotificationRequest notificationRequest) {
        kafkaService.send(notificationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}