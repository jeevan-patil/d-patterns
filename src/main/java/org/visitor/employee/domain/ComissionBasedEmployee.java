package org.visitor.employee.domain;

import java.math.BigDecimal;
import org.visitor.employee.Employee;
import org.visitor.employee.exception.InvalidDataException;
import org.visitor.employee.visitors.SalaryCalculatorVisitor;

public class ComissionBasedEmployee implements Employee {

  private final BigDecimal comissionRate;

  private final BigDecimal saleVolume;

  public ComissionBasedEmployee(final BigDecimal comissionRate, final BigDecimal saleVolume) {
    if (comissionRate.compareTo(BigDecimal.valueOf(100)) > 0) {
      throw new InvalidDataException("Comission rate cannot be greater and 100");
    }

    this.comissionRate = comissionRate;
    this.saleVolume = saleVolume;
  }

  public BigDecimal getComissionRate() {
    return comissionRate;
  }

  public BigDecimal getSaleVolume() {
    return saleVolume;
  }

  public BigDecimal calculateSalary(final SalaryCalculatorVisitor salaryCalculatorVisitor) {
    return salaryCalculatorVisitor.calculateSalary(this);
  }
}
