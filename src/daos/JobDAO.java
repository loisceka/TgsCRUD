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

/**
 *
 * @author loisceka
 */
public class JobDAO {

    private Connection connection;

    /**
     *
     * @param connection using connection to send query statement into mysql
     */
    public JobDAO(Connection connection) {
        this.connection = connection;
    }

    //GET ALL

    /**
     *
     * @return List Data of Job Table
     */
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

    /**
     *
     * @param job using object Job to insert into table Job
     * @return boolean - if statement executed will return true, if not will return false 
     */
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

    /**
     *
     * @param id parameter for updating data using primary key/id
     * @param job parameter for updating data using object Job
     * @return boolean - if statement executed will return true, if not will return false
     */
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

    /**
     *
     * @param id parameter for deleting data using primary key/id
     * @return boolean - if statement executed will return true, if not will return false
     */
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

    /**
     *
     * @param id parameter for getting data using primary key/id
     * @return Object Job - will return Job Data by ID or will return NULL if id invalid
     */
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
