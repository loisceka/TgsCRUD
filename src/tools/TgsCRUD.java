/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import daos.RegionDAO;

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
    }

}
