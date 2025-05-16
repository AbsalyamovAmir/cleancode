package ru.cleancode.notification.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationSender {
    private NotificationService notificationService;
    private final NotificationService emailService;
    private final NotificationService smsService;

    @Autowired
    public NotificationSender(
            @Qualifier("email") NotificationService emailService,
            @Qualifier("sms") NotificationService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
        this.notificationService = emailService;
    }

    public void notifyUser(String message) {
        notificationService.send(message);
    }

    public void setNotificationType(String type) {
        switch (type.toLowerCase()) {
            case "sms":
                this.notificationService = smsService;
                break;
            case "email":
            default:
                this.notificationService = emailService;
                break;
        }
    }
}