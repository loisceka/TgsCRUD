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

/**
 *
 * @author loisceka
 */
public class CountryDAO {

    private Connection connection;

    /**
     *
     * @param connection using connection to send query statement into mysql
     */
    public CountryDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     *
     * @return List Data of Country Table
     */
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

    /**
     *
     * @param country using object Country to insert or update into table Country, id from country will be used to check if ID used or not
     * @return boolean - if statement executed will return true, if not will return false
     */
    public boolean insertAndUpdate(Country country) {
        try {
            boolean isInsert = getById(country.getId()) == null;
            System.out.println(isInsert ? "Insert Berhasil" : "Update Berhasil");
            String query = isInsert
                    ? "INSERT INTO tb_country(country_name,region,country_id) VALUES(?,?,?)"
                    : "UPDATE tb_country SET country_name = ?, region = ? where country_id = ?";


            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, country.getName());
            preparedStatement.setInt(2, country.getRegion());
            preparedStatement.setString(3, country.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RegionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    /**
     *
     * @param id parameter for deleting data using primary key/id
     * @return boolean - if statement executed will return true, if not will return false
     */
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

    /**
     *
     * @param id parameter for getting data using primary key/id
     * @return Object Country - will return Country Data by ID or will return NULL if id invalid
     */
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
