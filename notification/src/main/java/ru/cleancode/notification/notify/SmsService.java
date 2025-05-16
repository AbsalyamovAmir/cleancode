package ru.cleancode.notification.notify;

import org.springframework.stereotype.Service;

@Service("sms")
public class SmsService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("Отправка SMS: " + message);
    }
}