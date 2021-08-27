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
import models.Country;
import models.Location;

/**
 *
 * @author loisceka
 */
public class LocationDAO {

    private Connection connection;

    public LocationDAO(Connection connection) {
        this.connection = connection;
    }

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

    public boolean insertAndUpdate(Location location) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Select * From tb_location where location_id = ?");
            ps.setString(1, location.getId());
            ResultSet set = ps.executeQuery();
            set.last();
            if (set.getRow() < 1) {
                ps = connection.prepareStatement("INSERT INTO tb_location (street_address,postal_code,city,state_province,country_id,location_id)VALUES(?,?,?,?,?,?)");
                System.out.println("INSERT\n");
            } else {
                ps = connection.prepareStatement("UPDATE tb_location SET street_address = ?, postal_code = ?,city=?,state_province=?,country_id=? where location_id = ?");
                System.out.println("UPDATE\n");

            }
            ps.setString(1, location.getStreet());
            ps.setString(2, location.getPostalCode());
            ps.setString(3, location.getCity());
            ps.setString(4, location.getProvince());
            ps.setString(5, location.getCountry());
            ps.setString(6, location.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RegionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

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
