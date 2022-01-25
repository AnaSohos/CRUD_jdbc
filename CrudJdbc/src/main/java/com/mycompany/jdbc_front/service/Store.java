package com.mycompany.jdbc_front.service;

import com.mycompany.jdbc_front.model.Position;
import com.mycompany.jdbc_front.model.Department;
import com.mycompany.jdbc_front.model.Employee;
import java.util.List;

public interface Store {

    Department insertDepartment(Department department);
    
    Position insertPosition( Position position);

    Employee insertEmployee(Employee employee);

    boolean updateEmployee(Employee employee);

    boolean delete(int id);

    List<Department> findAllDepartments();

    List<Employee> findAllEmployee();

    Employee findByIDEmployee(int id);

    Department findByNameDepartment(String name);
}
