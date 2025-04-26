package util.impl;

import model.Employee;
import util.inter.IEmployeeValidator;
import util.inter.ILogger;

/**
 * Реализация валидатора
 */
public class BasicEmployeeValidator implements IEmployeeValidator {
    private final ILogger logger;

    public BasicEmployeeValidator(ILogger logger) {
        this.logger = logger;
    }

    @Override
    public boolean validate(Employee emp) {
        if (emp == null || emp.getName() == null || emp.getName().isEmpty()) {
            logger.log("Invalid name");
            return false;
        }
        if (emp.getAge() <= 18 || emp.getAge() >= 65) {
            logger.log("Invalid age");
            return false;
        }
        if (emp.getSalary() <= 0) {
            logger.log("Invalid salary");
            return false;
        }
        return true;
    }
}
