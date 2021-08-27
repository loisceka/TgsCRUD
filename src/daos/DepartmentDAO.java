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
import models.Department;

/**
 *
 * @author loisceka
 */
public class DepartmentDAO {
    private Connection connection;

    public DepartmentDAO(Connection connection) {
        this.connection = connection;
    }
    
    //GET ALL
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();
        try {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM tb_department")
                    .executeQuery();
            while (resultSet.next()) {
                departments.add(new Department(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departments;
    }
    // LOCATION ID DAN MANAGER ID TIDAK BOLEH KOSONG 
    //INSERT
    public boolean insert(Department dep) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO tb_department(department_id, department_name, location_id, manager_id) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, dep.getId());
            preparedStatement.setString(2, dep.getName());
            preparedStatement.setString(3, dep.getLocationId());
            preparedStatement.setString(4, dep.getManagerId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //UPDATE
    public boolean update(String id, Department dep) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE tb_department SET department_id = ?, department_name = ?, location_id = ?, manager_id = ? WHERE department_id = ?");
            preparedStatement.setString(1, dep.getId());
            preparedStatement.setString(2, dep.getName());
            preparedStatement.setString(3, dep.getLocationId());
            preparedStatement.setString(4, dep.getManagerId());
            preparedStatement.setString(5, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //DELETE
    public boolean delete(String id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM tb_department WHERE department_id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //GET BY ID
    public Department getById(String id) {
        Department dep = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM tb_department WHERE department_id = ?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dep = new Department(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dep;
    }
}
