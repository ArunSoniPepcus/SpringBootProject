package com.pepcus.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pepcus.model.Employee;
import com.pepcus.service.EmployeeService;
import lombok.Data;

@Data
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  private EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    super();
    this.employeeService = employeeService;
  }

  /*
   *  mapping between the employee and address in a database
   */
  @PostMapping
  public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {

    return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
  }

  /* 
   * build get all employees
   */
  @GetMapping
  public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  // build get employee by id
  @GetMapping("{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
    return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
  }

  // build get employee by field shorting like employee name,email,phoneNumber
  @GetMapping("/sorts/{field}")
  public List<Employee> findEmployeeWithSorting(@PathVariable String field) {
    List<Employee> employeeSorting = employeeService.findEmployeeWithSorting(field);
    return employeeSorting;
  }

  // build get employee by name filter
  @GetMapping("/filters/{name}")
  public List<Employee> findEmployeeByFilter(@PathVariable String name) {
    List<Employee> employeeFilter = employeeService.findEmployeeByFilter(name);
    return employeeFilter;
  }

  // build update employee
  @PutMapping("{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
    return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
  }
/*
 this method build for delete employee
   */
  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
    employeeService.deleteEmployee(id);
    return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
  }
}
