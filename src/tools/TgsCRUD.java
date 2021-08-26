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
import models.Department;
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
        EmployeeDAO empdao = new EmployeeDAO(dbc.getConnection());
        DepartmentDAO depdao = new DepartmentDAO(dbc.getConnection());
        
//        System.out.println(depdao.insert(new Department("500B", "Consulant", "ID01", "1005")));
//        System.out.println(depdao.update("500B", new Department("50BC", "Throw", "ID02", "1006")));
//        System.out.println(depdao.delete("50BC"));
//        System.out.println(depdao.getById("100"));
        
        for (Department dep : depdao.getAll()) {
            System.out.println(dep.getId()+" - "+dep.getName());
        }
    }
}
