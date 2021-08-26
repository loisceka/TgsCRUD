/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;

/**
 *
 * @author loisceka
 */
public class DepartmentDAO {
    private Connection connection;

    public DepartmentDAO(Connection connection) {
        this.connection = connection;
    }
    
    
}
