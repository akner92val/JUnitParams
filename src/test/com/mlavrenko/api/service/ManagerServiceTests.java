package com.mlavrenko.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.mlavrenko.api.domain.Employee;
import java.math.BigDecimal;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

@RunWith(JUnitParamsRunner.class)
@SpringBootTest()
public class ManagerServiceTests {

    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private ManagerService managerService;

    @Test
    @Parameters({"2, true", "0, false", "3, false", "1, false", "10, true", "15, false", "12, true"})
    @TestCaseName("should give promotion for each 2 years of work")
    public void testGivePromotion(int workedYears, boolean expected) {
        final Employee employee = new Employee(workedYears, BigDecimal.TEN);
        final boolean wasPromoted = managerService.givePromotion(employee);
        assertThat(wasPromoted).isEqualTo(expected);
    }

    @Test
    @Parameters(method = "params")
    @TestCaseName("should hire employee {1} with salary {2} if his age is between 18 and 65")
    public void testHireEmployee(boolean expected, Employee employee, BigDecimal salary) {
        final boolean wasHired = managerService.hireEmployee(employee, salary);
        assertThat(wasHired).isEqualTo(expected);
        if (expected) {
            assertThat(employee.getSalary()).isEqualByComparingTo(salary);
            assertThat(employee.getAge()).isBetween(18, 65);
        }


    }

    @SuppressWarnings("unused")
    private Object[][] params() {
        return new Object[][]{
                new Object[]{true, new Employee(20), BigDecimal.ONE},
                new Object[]{false, new Employee(15), BigDecimal.ZERO},
                new Object[]{false, new Employee(80), BigDecimal.ZERO}
        };
    }
}
