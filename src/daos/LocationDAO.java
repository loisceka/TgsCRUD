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
import models.Location;

/**
 *
 * @author loisceka
 */
public class LocationDAO {

    private Connection connection;

    /**
     *
     * @param connection using connection to send query statement into mysql
     */
    public LocationDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     *
     * @return List Data of Location Table
     */
    public List<Location> getAll() {
        List<Location> locations = new ArrayList<>();
        try {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM tb_location")
                    .executeQuery();
            while (resultSet.next()) {
                locations.add(new Location(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locations;
    }

    /**
     *
     * @param location using object Location to insert or update into table Location, id from location will be used to check if ID used or not
     * @return boolean - if statement executed will return true, if not will return false
     */
    public boolean insertAndUpdate(Location location) {
        try {
            boolean isInsert = getById(location.getId()) == null;
            System.out.println(isInsert ? "Insert Berhasil" : "Update Berhasil");
            String query = isInsert
                    ? "INSERT INTO tb_location (street_address,postal_code,city,state_province,country_id,location_id)VALUES(?,?,?,?,?,?)"
                    : "UPDATE tb_location SET street_address = ?, postal_code = ?,city=?,state_province=?,country_id=? where location_id = ?";


            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, location.getStreet());
            preparedStatement.setString(2, location.getPostalCode());
            preparedStatement.setString(3, location.getCity());
            preparedStatement.setString(4, location.getProvince());
            preparedStatement.setString(5, location.getCountry());
            preparedStatement.setString(6, location.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
       
    }

    /**
     *
     * @param id parameter for getting data using primary key/id
     * @return Object Location - will return Location Data by ID or will return NULL if id invalid
     */
    public Location getById(String id) {
        Location location = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM tb_location WHERE location_id = ?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                location = new Location(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    /**
     *
     * @param id parameter for deleting data using primary key/id
     * @return boolean - if statement executed will return true, if not will return false
     */
    public boolean delete(String id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM tb_location WHERE location_id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
