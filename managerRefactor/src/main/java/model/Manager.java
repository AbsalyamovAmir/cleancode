package model;

public class Manager extends Employee {

    @Override
    public void setSalary(double salary) {
        if (salary < 5000) {
            throw new IllegalArgumentException("Manager salary too low");
        }
        super.setSalary(salary);
    }
}