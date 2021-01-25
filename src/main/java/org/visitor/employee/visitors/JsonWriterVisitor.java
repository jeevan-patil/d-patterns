package org.visitor.employee.visitors;

import org.visitor.employee.domain.ComissionAndFixedSalariedEmployee;
import org.visitor.employee.domain.ComissionBasedEmployee;
import org.visitor.employee.domain.SalariedEmployee;

public interface JsonWriterVisitor {

  String printJson(SalariedEmployee salariedEmployee);

  String printJson(ComissionBasedEmployee comissionBasedEmployee);

  String printJson(
      ComissionAndFixedSalariedEmployee comissionAndFixedSalariedEmployee);
}
