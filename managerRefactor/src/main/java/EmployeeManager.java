import model.Employee;
import service.inter.NotificationService;
import util.inter.IDatabaseConnection;
import util.inter.IEmployeeValidator;
import util.inter.ILogger;
import util.inter.IReportGenerator;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
    private final List<Employee> employees;
    private final IDatabaseConnection dbConnection;
    private final IEmployeeValidator validator;
    private final NotificationService notificationService;
    private final ILogger logger;

    public EmployeeManager(IDatabaseConnection dbConnection,
                           IEmployeeValidator validator,
                           NotificationService notificationService,
                           ILogger logger) {
        this.employees = new ArrayList<>();
        this.dbConnection = dbConnection;
        this.validator = validator;
        this.notificationService = notificationService;
        this.logger = logger;
    }

    public void processEmployeeData(Employee emp) {
        if (!validator.validate(emp)) {
            return;
        }

        if (isEmployeeExists(emp.getId())) {
            logger.log("Employee exists");
            return;
        }

        if (!dbConnection.isConnected()) {
            logger.log("DB not connected");
            return;
        }

        dbConnection.save(emp);
        employees.add(emp);
        notificationService.sendNotification(emp);
        logger.log("Added: " + emp.getName());
    }

    private boolean isEmployeeExists(int id) {
        return employees.stream().anyMatch(e -> e.getId() == id);
    }

    public void generateReport(IReportGenerator reportGenerator) {
        reportGenerator.generate();
    }
}
