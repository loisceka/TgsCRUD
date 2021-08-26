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
import models.Job;
import models.Region;

/**
 *
 * @author loisceka
 */
public class JobDAO {

    private Connection connection;

    public JobDAO(Connection connection) {
        this.connection = connection;
    }
    
    //GET ALL
    public List<Job> getAll() {
        List<Job> jobs = new ArrayList<>();
        try {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM tb_job")
                    .executeQuery();
            while (resultSet.next()) {
                jobs.add(new Job(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobs;
    }
    
    //INSERT
    public boolean insert(Job job) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tb_job(job_id, job_title, min_salary, max_salary) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, job.getId());
            preparedStatement.setString(2, job.getName());
            preparedStatement.setInt(3, job.getMinSalary());
            preparedStatement.setInt(4, job.getMaxSalary());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //UPDATE
    public boolean update(String id, Job job) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE tb_job SET job_id = ?, job_title = ?, min_salary = ?, max_salary = ? WHERE job_id = ?");
            preparedStatement.setString(1, job.getId());
            preparedStatement.setString(2, job.getName());
            preparedStatement.setInt(3, job.getMinSalary());
            preparedStatement.setInt(4, job.getMaxSalary());
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
                    .prepareStatement("DELETE FROM tb_job WHERE job_id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //GET BY ID
    public Job getById(String id) {
        Job job = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM tb_job WHERE job_id = ?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                job = new Job(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return job;
    }
}
