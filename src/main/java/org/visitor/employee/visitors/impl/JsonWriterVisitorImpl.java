package org.visitor.employee.visitors.impl;

import org.visitor.employee.domain.ComissionAndFixedSalariedEmployee;
import org.visitor.employee.domain.ComissionBasedEmployee;
import org.visitor.employee.domain.SalariedEmployee;
import org.visitor.employee.visitors.JsonWriterVisitor;

public class JsonWriterVisitorImpl implements JsonWriterVisitor {

  @Override
  public String printJson(final SalariedEmployee salariedEmployee) {
    return "{\"salary\" : " + salariedEmployee.getSalary() + "}";
  }

  @Override
  public String printJson(final ComissionBasedEmployee comissionBasedEmployee) {
    return "{\"comissionRate\" : " + comissionBasedEmployee.getComissionRate()
        + ", \"saleVolume\" : " + comissionBasedEmployee.getSaleVolume() + "}";
  }

  @Override
  public String printJson(
      final ComissionAndFixedSalariedEmployee comissionAndFixedSalariedEmployee) {
    return "{\"comissionRate\" : " + comissionAndFixedSalariedEmployee.getComissionRate()
        + ", \"saleVolume\" : " + comissionAndFixedSalariedEmployee.getSaleVolume()
        + ", \"salary\" : " + comissionAndFixedSalariedEmployee.getSalary() + "}";
  }
}
