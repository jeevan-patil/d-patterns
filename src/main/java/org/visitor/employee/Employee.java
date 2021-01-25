package org.visitor.employee;

import java.math.BigDecimal;
import org.visitor.employee.visitors.SalaryCalculatorVisitor;

public interface Employee {

  BigDecimal calculateSalary(SalaryCalculatorVisitor salaryCalculatorVisitor);
}
