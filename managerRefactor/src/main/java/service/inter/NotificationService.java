package service.inter;

import model.Employee;

/**
 * Интерфейс для нотификаций
 */
public interface NotificationService {
    void sendNotification(Employee emp);
}
