package com.mlavrenko.api.service;

import com.mlavrenko.api.domain.Employee;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    boolean givePromotion(Employee employee) {
        final int workedYears = employee.getWorkedYears();
        if (workedYears > 0 && workedYears % 2 == 0) {
            employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.1)));
            return true;
        }
        return false;
    }

    boolean hireEmployee(Employee employee, BigDecimal salary) {
        final int age = employee.getAge();
        if (age > 18 && age < 65) {
            employee.setSalary(salary);
            employee.setWorkedYears(0);
            return true;
        }
        return false;
    }
}
