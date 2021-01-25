package org.visitor.employee.visitors;

import java.math.BigDecimal;
import org.visitor.employee.domain.ComissionAndFixedSalariedEmployee;
import org.visitor.employee.domain.ComissionBasedEmployee;
import org.visitor.employee.domain.SalariedEmployee;

public interface SalaryCalculatorVisitor {

  BigDecimal calculateSalary(SalariedEmployee salariedEmployee);

  BigDecimal calculateSalary(ComissionBasedEmployee comissionBasedEmployee);

  BigDecimal calculateSalary(
      ComissionAndFixedSalariedEmployee comissionAndFixedSalariedEmployee);
}
