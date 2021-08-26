/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import daos.DepartmentDAO;
import daos.EmployeeDAO;
import daos.JobDAO;
import daos.RegionDAO;
import models.Job;
import models.Region;

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
        JobDAO jdao = new JobDAO(dbc.getConnection());
        EmployeeDAO emdao = new EmployeeDAO(dbc.getConnection());
        DepartmentDAO dedao = new DepartmentDAO(dbc.getConnection());
        
//          System.out.println(jdao.insert(new Job("A2000", "Tester", 400, 1000)));
//        System.out.println(jdao.update("A2000", new Job("A2100", "Consultant", 600, 1500)));
//        System.out.println(jdao.delete("A2100"));
//        System.out.println(jdao.getById("A1000"));
        
        for (Job j : jdao.getAll()) {
            System.out.println(j.getId()+" - "+j.getName());
        }
    }
}
