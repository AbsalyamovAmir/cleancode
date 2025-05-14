package ru.cleancode.notification.notify;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class NotificationConfig {

    @Bean
    @Primary // Будет использоваться по умолчанию, если не указано иное
    public NotificationService notificationService() {
        // Здесь можно вернуть реализацию по умолчанию
        return new EmailService(); // или SmsService()
    }
}
