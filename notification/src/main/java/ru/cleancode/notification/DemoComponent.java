package ru.cleancode.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.cleancode.notification.notify.EmailService;
import ru.cleancode.notification.notify.NotificationSender;
import ru.cleancode.notification.notify.SmsService;

@Component
@RequiredArgsConstructor
public class DemoComponent implements CommandLineRunner {

    private final NotificationSender notificationSender;
    private final SmsService smsService;
    private final EmailService emailService;

    @Value("${notify.mode}")
    private String notificationMode;

    @Override
    public void run(String... args) throws Exception {
        switch (notificationMode.toLowerCase()) {
            case "sms":
                notificationSender.setNotificationService(smsService);
                break;
            case "email":
                notificationSender.setNotificationService(emailService);
                break;
        }

        notificationSender.notifyUser("Hello!!!");
    }
}
