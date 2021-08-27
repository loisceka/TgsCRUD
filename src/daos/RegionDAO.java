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
     * @param region
     * @return
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
     * @param id
     * @param region
     * @return
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
     * @param region
     * @return
     */
    public boolean insertAndUpdate(Region region) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO tb_region(id, region_name) VALUES (?, ?) ON DUPLICATE KEY UPDATE region_name = ?");
            preparedStatement.setInt(1, region.getId());
            preparedStatement.setString(2, region.getName());
            preparedStatement.setString(3, region.getName());
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
     * @param id
     * @return
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
     * @param id
     * @return
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
