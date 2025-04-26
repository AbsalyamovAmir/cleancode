package util.inter;

import model.Employee;

/**
 * Интерфейс для работы с БД
 */
public interface IDatabaseConnection {
    boolean isConnected();
    void save(Employee emp);
}
