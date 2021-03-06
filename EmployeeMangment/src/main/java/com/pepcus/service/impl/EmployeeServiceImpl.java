package com.pepcus.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.pepcus.exception.ResourceNotFoundException;
import com.pepcus.model.Address;
import com.pepcus.model.Employee;
import com.pepcus.repository.EmployeeRepository;
import com.pepcus.service.EmployeeService;

@Service

public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    super();
    this.employeeRepository = employeeRepository;
  }
  @Transactional
  
  @Override
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }
  @Override
  @Transactional
  public Employee saveEmployee(Employee employee) {
    List<Address> addressList = new ArrayList<>();
    employee.getAddressList().stream().map(a -> addressList.add(a)).collect(Collectors.toList());
    employeeRepository.save(employee);
    return employee;
  }

  @Override
  public Employee getEmployeeById(long id) {
    return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
  }

  @Override
  public Employee updateEmployee(Employee employee, long id) {

    // we need to check whether employee with given id is exist in DB or not
    Employee existingEmployee = employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

    existingEmployee.setName(employee.getName());
    existingEmployee.setEmail(employee.getEmail());
    existingEmployee.setPhoneNumber(employee.getPhoneNumber());

    existingEmployee.setAddressList(employee.getAddressList());

    // save existing employee to DB
    employeeRepository.save(existingEmployee);
    return existingEmployee;
  }

  @Override
  public void deleteEmployee(long id) {
    // check whether a employee exist in a DB or not
    employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
    employeeRepository.deleteById(id);
  }

  // ascending order to sorting in database
  public List<Employee> findEmployeeWithSorting(String field) {
    return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, field));
  }

  // filter in database and search name and email
  public List<Employee> findEmployeeByFilter(String name) {
    List<Employee> filterData = new ArrayList<>();
    List<Employee> employees = employeeRepository.findAll();
    for (Employee employeeFindData : employees) {
      if (employeeFindData.getName().equalsIgnoreCase(name) || employeeFindData.getPhoneNumber().equalsIgnoreCase(name)
          || employeeFindData.getEmail().equalsIgnoreCase(name) || employeeFindData.getDepartment().equalsIgnoreCase(name)) {
        filterData.add(employeeFindData);
      }
      for (Address employeFindDataAddress : employeeFindData.getAddressList()) {
        if (employeFindDataAddress.getCity().equalsIgnoreCase(name)
            || employeFindDataAddress.getCountry().equalsIgnoreCase(name)
            || employeFindDataAddress.getState().equalsIgnoreCase(name)) {
          filterData.add(employeeFindData);
        }
      }
    }
    return filterData;
  }
}
