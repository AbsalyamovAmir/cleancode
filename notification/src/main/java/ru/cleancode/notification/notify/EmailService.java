package ru.cleancode.notification.notify;

import org.springframework.stereotype.Service;

@Service("email")
public class EmailService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("Отправка Email: " + message);
    }
}