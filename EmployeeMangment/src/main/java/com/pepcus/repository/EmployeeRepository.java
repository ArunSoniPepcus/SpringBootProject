package com.pepcus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.pepcus.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  
//  public List<Employee> findBydepartment_type(String department_type);

}