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
import models.Region;

/**
 *
 * @author loisceka
 */
public class CountryDAO {

    private Connection connection;

    public CountryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Country> getAll() {
        List<Country> countries = new ArrayList<>();
        try {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM tb_country")
                    .executeQuery();
            while (resultSet.next()) {
                countries.add(new Country(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }

    public boolean insertAndUpdate(Country country) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Select * From tb_country where country_id = ?");
            ps.setString(1, country.getId());
            ResultSet set = ps.executeQuery();
            set.last();
            if (set.getRow() < 1) {
                ps = connection.prepareStatement("INSERT INTO tb_country(country_name,region,country_id) VALUES(?,?,?)");
                System.out.println("INSERT\n");
            } else {
                ps = connection.prepareStatement("UPDATE tb_country SET country_name = ?, region = ? where country_id = ?");
                System.out.println("UPDATE\n");

            }
            ps.setString(1, country.getName());
            ps.setInt(2, country.getRegion());
            ps.setString(3, country.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RegionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean delete(String id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM tb_country WHERE country_id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Country getById(String id) {
        Country country = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM tb_country WHERE country_id = ?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                country = new Country(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }
}
