package org.visitor.employee;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.visitor.employee.domain.ComissionAndFixedSalariedEmployee;
import org.visitor.employee.domain.ComissionBasedEmployee;
import org.visitor.employee.domain.SalariedEmployee;
import org.visitor.employee.exception.InvalidDataException;
import org.visitor.employee.visitors.SalaryCalculatorVisitor;
import org.visitor.employee.visitors.impl.SalaryCalculatorVisitorImpl;

public class EmployeeSalaryCalculatorTest {

  private final BigDecimal SALARY = BigDecimal.valueOf(2344);

  private final BigDecimal COMISSION_RATE = BigDecimal.valueOf(6.7);

  private final BigDecimal SALE_VOLUME = BigDecimal.valueOf(2344);

  final SalaryCalculatorVisitor calculator = new SalaryCalculatorVisitorImpl();

  /**
   * Test salaried employee creation
   */
  @Test
  public void shouldCreateSalariedEmployee() {

    final SalariedEmployee salariedEmployee = new SalariedEmployee(SALARY);
    assertEquals(SALARY, salariedEmployee.getSalary());
  }

  /**
   * Test comissioned employee creation
   */
  @Test
  public void shouldCreateComissionBasedEmployee() {

    final ComissionBasedEmployee comissionBasedEmployee = new ComissionBasedEmployee(COMISSION_RATE,
        SALE_VOLUME);
    assertEquals(COMISSION_RATE, comissionBasedEmployee.getComissionRate());
    assertEquals(SALE_VOLUME, comissionBasedEmployee.getSaleVolume());
  }

  /**
   * Test comissioned and salaried employee creation
   */
  @Test
  public void shouldCreateComissionBasedAndSalariedEmployee() {

    final ComissionAndFixedSalariedEmployee employee = new ComissionAndFixedSalariedEmployee(SALARY,
        COMISSION_RATE, SALE_VOLUME);
    assertEquals(COMISSION_RATE, employee.getComissionRate());
    assertEquals(SALE_VOLUME, employee.getSaleVolume());
    assertEquals(SALARY, employee.getSalary());
  }

  /**
   * Test if the comission rate is greater than 100
   */
  @Test(expected = InvalidDataException.class)
  public void shouldFailIfComissionRateIsGreaterThan100ForComissionedEmployee() {
    final ComissionBasedEmployee comissionBasedEmployee = new ComissionBasedEmployee(
        BigDecimal.valueOf(101),
        SALE_VOLUME);
  }

  /**
   * Test if the comission rate is greater than 100
   */
  @Test(expected = InvalidDataException.class)
  public void shouldFailIfComissionRateIsGreaterThan100ForMixedIncomeEmployee() {
    final ComissionAndFixedSalariedEmployee comissionBasedEmployee = new ComissionAndFixedSalariedEmployee(
        SALARY, BigDecimal.valueOf(101), SALE_VOLUME);
  }

  /**
   * Test the salary calculation of salaried employee
   */
  @Test
  public void shouldCalculateSalaryOfSalariedEmployee() {
    final SalariedEmployee salariedEmployee = new SalariedEmployee(SALARY);
    final BigDecimal salary = calculator.calculateSalary(salariedEmployee);
    assertEquals(SALARY, salary);
  }

  /**
   * Test the salary calculation of comissioned employee
   */
  @Test
  public void shouldCalculateSalaryOfComissionedEmployee() {
    final ComissionBasedEmployee comissionBasedEmployee = new ComissionBasedEmployee(COMISSION_RATE,
        SALE_VOLUME);
    final BigDecimal salary = calculator.calculateSalary(comissionBasedEmployee);
    final BigDecimal expected = SALE_VOLUME.divide(COMISSION_RATE, 2, RoundingMode.HALF_UP);

    assertEquals(expected, salary);
  }

  /**
   * Test the salary of fixed salary and comissioned employee
   */
  @Test
  public void shouldCalculateSalaryOfComissionedAndFixedSalariedEmployee() {
    final ComissionAndFixedSalariedEmployee employee = new ComissionAndFixedSalariedEmployee(SALARY,
        COMISSION_RATE, SALE_VOLUME);
    final BigDecimal salary = calculator.calculateSalary(employee);
    final BigDecimal expected = SALE_VOLUME.divide(COMISSION_RATE, 2, RoundingMode.HALF_UP)
        .add(employee.getSalary());

    assertEquals(expected, salary);
  }

  /**
   * Calculate sum of all employee's salary
   */
  @Test
  public void calculateSumOfEmployeeSalary() {
    final SalariedEmployee salariedEmployee = new SalariedEmployee(SALARY);
    final ComissionBasedEmployee comissionBasedEmployee = new ComissionBasedEmployee(COMISSION_RATE,
        SALE_VOLUME);
    final ComissionAndFixedSalariedEmployee employee = new ComissionAndFixedSalariedEmployee(SALARY,
        COMISSION_RATE, SALE_VOLUME);

    final List<Employee> employees = new ArrayList<>();
    employees.add(salariedEmployee);
    employees.add(comissionBasedEmployee);
    employees.add(employee);
    final BigDecimal sum = calculateSumOfSalaries(employees);
    System.out.println("Sum of salaries of employees is: " + sum);
  }

  private BigDecimal calculateSumOfSalaries(final List<Employee> employees) {
    return employees.stream().map(employee -> employee.calculateSalary(calculator))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
