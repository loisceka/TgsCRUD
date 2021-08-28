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
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Region;

/**
 *
 * @author loisceka
 */
public class RegionDAO {

    private Connection connection;

    /**
     *
     * @param connection using connection to send query statement into mysql
     */
    public RegionDAO(Connection connection) {
        this.connection = connection;
    }

    //GET ALL

    /**
     *
     * @return List Data of Region Table
     */
    public List<Region> getAll() {
        List<Region> regions = new ArrayList<>();
        try {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM tb_region")
                    .executeQuery();
            while (resultSet.next()) {
                regions.add(new Region(resultSet.getInt("id"), resultSet.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regions;
    }

    //INSERT

    /**
     *
     * @param region using object Region to insert into table Region
     * @return boolean - if statement executed will return true, if not will
     * return false
     */
    public boolean insert(Region region) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tb_region(id, region_name) VALUES (?, ?)");
            preparedStatement.setInt(1, region.getId());
            preparedStatement.setString(2, region.getName());
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
     * @param region parameter for updating data using object Region
     * @return boolean - if statement executed will return true, if not will
     * return false
     */
    public boolean update(Integer id, Region region) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE tb_region SET id = ?, region_name = ? WHERE id = ?");
            preparedStatement.setInt(1, region.getId());
            preparedStatement.setString(2, region.getName());
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //New Method

    /**
     *
     * @param region using Region class named region to get ID for insert or update data
     * @return boolean - if statement executed will return true, if not will
     * return false
     */
    public boolean insertAndUpdate(Region region) {
        try {
            boolean isInsert = getById(region.getId()) == null;
            System.out.println(isInsert ? "Insert Berhasil" : "Update Berhasil");
            String query = isInsert
                    ? "INSERT INTO tb_region(region_name, id) VALUES (?, ?)"
                    : "UPDATE tb_region SET region_name = ? WHERE region_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, region.getName());
            preparedStatement.setInt(2, region.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //DELETE

    /**
     *
     * @param id parameter for deleting data using primary key/id
     * @return boolean - if statement executed will return true, if not will
     * return false
     */
    public boolean delete(Integer id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM tb_region WHERE id = ?");
            preparedStatement.setInt(1, id);
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
     * @return Object Region - will return Region Data by ID or will return NULL if id
     * invalid
     */
    public Region getById(Integer id) {
        Region region = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM tb_region WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                region = new Region(resultSet.getInt(1), resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return region;
    }
}
