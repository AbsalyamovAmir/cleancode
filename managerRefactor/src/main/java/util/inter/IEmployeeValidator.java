package util.inter;

import model.Employee;

/**
 * Интерфейс для валидации
 */
public interface IEmployeeValidator {
    boolean validate(Employee emp);
}
