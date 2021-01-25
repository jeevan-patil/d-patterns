package org.visitor.employee.domain;

import java.math.BigDecimal;
import org.visitor.employee.Employee;
import org.visitor.employee.visitors.SalaryCalculatorVisitor;

public class SalariedEmployee implements Employee {

  private final BigDecimal salary;

  public SalariedEmployee(final BigDecimal salary) {
    this.salary = salary;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public BigDecimal calculateSalary(final SalaryCalculatorVisitor salaryCalculatorVisitor) {
    return salaryCalculatorVisitor.calculateSalary(this);
  }
}
