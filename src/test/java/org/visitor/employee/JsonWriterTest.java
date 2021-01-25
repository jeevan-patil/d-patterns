package org.visitor.employee;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import org.visitor.employee.domain.ComissionAndFixedSalariedEmployee;
import org.visitor.employee.domain.ComissionBasedEmployee;
import org.visitor.employee.domain.SalariedEmployee;
import org.visitor.employee.visitors.JsonWriterVisitor;
import org.visitor.employee.visitors.impl.JsonWriterVisitorImpl;

public class JsonWriterTest {

  private final BigDecimal SALARY = BigDecimal.valueOf(2344);

  private final BigDecimal COMISSION_RATE = BigDecimal.valueOf(6.7);

  private final BigDecimal SALE_VOLUME = BigDecimal.valueOf(2344);

  final JsonWriterVisitor jsonWriter = new JsonWriterVisitorImpl();

  @Test
  public void shouldPrintSalariedEmployeeJson() {

    final SalariedEmployee salariedEmployee = new SalariedEmployee(SALARY);
    final String json = jsonWriter.printJson(salariedEmployee);
    System.out.println(json);
    Assert.assertEquals("{\"salary\" : 2344}", json);
  }

  /**
   * Test comissioned employee creation
   */
  @Test
  public void shouldCreateComissionBasedEmployee() {

    final ComissionBasedEmployee comissionBasedEmployee = new ComissionBasedEmployee(COMISSION_RATE,
        SALE_VOLUME);
    final String json = jsonWriter.printJson(comissionBasedEmployee);
    System.out.println(json);
    Assert.assertEquals("{\"comissionRate\" : 6.7, \"saleVolume\" : 2344}", json);
  }

  /**
   * Test comissioned and salaried employee creation
   */
  @Test
  public void shouldCreateComissionBasedAndSalariedEmployee() {

    final ComissionAndFixedSalariedEmployee employee = new ComissionAndFixedSalariedEmployee(SALARY,
        COMISSION_RATE, SALE_VOLUME);
    final String json = jsonWriter.printJson(employee);
    System.out.println(json);
    Assert
        .assertEquals("{\"comissionRate\" : 6.7, \"saleVolume\" : 2344, \"salary\" : 2344}", json);
  }
}
