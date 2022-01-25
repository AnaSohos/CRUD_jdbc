package com.mycompany.jdbc_front.service;


import com.mycompany.jdbc_front.model.Department;
import com.mycompany.jdbc_front.model.Employee;
import com.mycompany.jdbc_front.model.Position;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PsqlStore implements Store {

    private Connection cn;

    public PsqlStore(Connection cn) {
        this.cn = cn;
    }

    public PsqlStore() {
      
    }

    @Override
    public Department insertDepartment(Department department) {
        try ( PreparedStatement statement = cn.prepareStatement(
                "insert into department(name, email, phone) values (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )) {
            statement.setString(1, department.getName());
            statement.setString(2, department.getEmail());
            statement.setString(3, department.getPhone());
             
            statement.execute();
            try ( ResultSet generateKey = statement.getGeneratedKeys()) {
                if (generateKey.next()) {
                    department.setId(generateKey.getInt(1));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return department;
    }
        @Override
    public Position insertPosition(Position position) {
        try ( PreparedStatement statement = cn.prepareStatement(
                "insert into position(name, salary) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )) {
            statement.setString(1, position.getName());
            statement.setInt(2, position.getSalary());
             
            statement.execute();
            try ( ResultSet generateKey = statement.getGeneratedKeys()) {
                if (generateKey.next()) {
                    position.setId(generateKey.getInt(1));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return position;
    }

    @Override
    public Employee insertEmployee(Employee employee) {
        try ( PreparedStatement statement = cn.prepareStatement(
                "insert into employee(name, dept_id, post_id) values (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )) {
            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getDeptId());
            statement.setInt(3, employee.getPostId());
            statement.execute();
            try ( ResultSet generateKey = statement.getGeneratedKeys()) {
                if (generateKey.next()) {
                    employee.setId(generateKey.getInt(1));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employee;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        boolean rsl = false;
        try ( PreparedStatement statement = cn.prepareStatement(
                "update employee set name = ?, dept_id = ? where id = ?"
        )) {
            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getDeptId());
            statement.setInt(3, employee.getId());
            rsl = statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        try ( PreparedStatement statement = cn.prepareStatement(
                "delete from employee where id = ?"
        )) {
            statement.setInt(1, id);
            rsl = statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Department> findAllDepartments() {
        List<Department> departments = new ArrayList<>();
        try ( PreparedStatement statement = cn.prepareStatement( "select * from department"  

            )) {
 try ( ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    departments.add(
                            Department.of(
                                    resultSet.getInt("id"),
                                    resultSet.getString("name")
                            )
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public List<Employee> findAllEmployee() {
        List<Employee> employee = new ArrayList<>();
        try ( PreparedStatement statement = cn.prepareStatement( "select * from employee"  )) {
 try ( ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    employee.add(
                            Employee.of(
                                    resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getInt("dept_id"),
                                    resultSet.getInt("post_id")
                            )
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee findByIDEmployee(int id) {
        Employee employee = null;
        try ( PreparedStatement statement = cn.prepareStatement( 
            "select * from employee where id =  ?")) {
 statement.setInt(1, id);
            try ( ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    employee = Employee.of(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("dept_id"),
                            resultSet.getInt("post_id")
                    );
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employee;
    }

    @Override
    public Department findByNameDepartment(String name) {
        Department department = null;
        try ( PreparedStatement statement = cn.prepareStatement( 
          "select * from department where name =  ?" )) {
 statement.setString(1, name);
            try ( ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    department = department.of(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    );
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return department;
    }
}
