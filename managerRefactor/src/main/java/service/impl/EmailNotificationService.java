package service.impl;

import model.Employee;
import service.inter.NotificationService;

/**
 * Реализация сервиса уведомлений
 */
public class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(Employee emp) {
        if (emp != null && emp.getEmail() != null && !emp.getEmail().isEmpty()) {
            System.out.println("Sending email to " + emp.getEmail());
        }
    }
}
