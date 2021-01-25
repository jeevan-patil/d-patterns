package org.visitor.employee.visitors.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.visitor.employee.domain.ComissionAndFixedSalariedEmployee;
import org.visitor.employee.domain.ComissionBasedEmployee;
import org.visitor.employee.domain.SalariedEmployee;
import org.visitor.employee.visitors.SalaryCalculatorVisitor;

public class SalaryCalculatorVisitorImpl implements SalaryCalculatorVisitor {

  public BigDecimal calculateSalary(final SalariedEmployee salariedEmployee) {
    return salariedEmployee.getSalary();
  }

  public BigDecimal calculateSalary(final ComissionBasedEmployee comissionBasedEmployee) {
    return comissionBasedEmployee.getSaleVolume()
        .divide(comissionBasedEmployee.getComissionRate(), 2, RoundingMode.HALF_UP);
  }

  public BigDecimal calculateSalary(
      final ComissionAndFixedSalariedEmployee comissionAndFixedSalariedEmployee) {
    return comissionAndFixedSalariedEmployee.getSaleVolume()
        .divide(comissionAndFixedSalariedEmployee.getComissionRate(), 2, RoundingMode.HALF_UP)
        .add(comissionAndFixedSalariedEmployee.getSalary());
  }
}
