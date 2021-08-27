/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import daos.CountryDAO;
import daos.LocationDAO;
import daos.RegionDAO;
import models.Country;
import models.Location;

/**
 *
 * @author loisceka
 */
public class TgsCRUD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBConnection dbc = new DBConnection();
        //Test Connection
        System.out.println(dbc.getConnection());

        //Dependency Injection
        RegionDAO rdao = new RegionDAO(dbc.getConnection());
        CountryDAO cdao = new CountryDAO(dbc.getConnection());
        LocationDAO ldao = new LocationDAO(dbc.getConnection());
    }

}
