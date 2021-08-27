/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Employee;

/**
 *
 * @author loisceka
 */
public class EmployeeDAO {

    private Connection connection;

    /**
     *
     * @param connection using connection to send query statement into mysql
     */
    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    
    //GET ALL

    /**
     *
     * @return List Data of Employee Table
     */
    public List<Employee> getAll() {
        List<Employee> emp = new ArrayList<>();
        try {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM tb_employee")
                    .executeQuery();
            while (resultSet.next()) {
                emp.add(new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                         resultSet.getString(5), resultSet.getDate(6), resultSet.getInt(7), resultSet.getDouble(8), resultSet.getString(9),
                         resultSet.getString(10), resultSet.getString(11)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

    // 
    //INSERT

    /**
     *
     * @param emp using object Employee to insert into table employee
     * @return boolean - if statement executed will return true, if not will return false 
     */
    public boolean insert(Employee emp) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO tb_employee(employee_id, first_name, last_name, email, phone_number, hire_date, "
                            + "salary, commision_pct, job_id, manager_id, department_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, emp.getId());
            preparedStatement.setString(2, emp.getFirstName());
            preparedStatement.setString(3, emp.getLastName());
            preparedStatement.setString(4, emp.getEmail());
            preparedStatement.setString(5, emp.getPhone());
            preparedStatement.setDate(6, emp.getHireDate());
            preparedStatement.setInt(7, emp.getSalary());
            preparedStatement.setDouble(8, emp.getCommision());
            preparedStatement.setString(9, emp.getJobId());
            preparedStatement.setString(10, emp.getManagerId());
            preparedStatement.setString(11, emp.getDepartmentId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //UPDATE

    /**
     *
     * @param id parameter for updating data using primary key/id
     * @param emp parameter for updating data using object Employee
     * @return boolean - if statement executed will return true, if not will return false
     */
    public boolean update(String id, Employee emp) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE tb_employee SET employee_id = ?, first_name = ?, last_name = ?, email = ?, phone_number = ?, hire_date = ?,"
                            + "salary = ?, commision_pct = ?,  job_id = ?, manager_id = ?, department_id = ? WHERE employee_id = ?");
            preparedStatement.setString(1, emp.getId());
            preparedStatement.setString(2, emp.getFirstName());
            preparedStatement.setString(3, emp.getLastName());
            preparedStatement.setString(4, emp.getEmail());
            preparedStatement.setString(5, emp.getPhone());
            preparedStatement.setDate(6, emp.getHireDate());
            preparedStatement.setInt(7, emp.getSalary());
            preparedStatement.setDouble(8, emp.getCommision());
            preparedStatement.setString(9, emp.getJobId());
            preparedStatement.setString(10, emp.getManagerId());
            preparedStatement.setString(11, emp.getDepartmentId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //DELETE

    /**
     *
     * @param id parameter for deleting data using primary key/id
     * @return boolean - if statement executed will return true, if not will return false
     */
    public boolean delete(String id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM tb_employee WHERE employee_id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //GET BY ID

    /**
     *
     * @param id parameter for getting data using primary key/id
     * @return Object Employee - will return Employee Data by ID or will return NULL if id invalid
     */
    public Employee getById(String id) {
        Employee emp = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM tb_employee WHERE employee_id = ?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                emp = new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                         resultSet.getString(5), resultSet.getDate(6), resultSet.getInt(7), resultSet.getDouble(8), resultSet.getString(9),
                         resultSet.getString(10), resultSet.getString(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

}
