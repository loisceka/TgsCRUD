/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author loisceka
 */
public class Department {

    private String id;
    private String name;
    private String locationId;
    private String managerId;

    public Department() {
    }

    public Department(String id, String name, String locationId, String managerId) {
        this.id = id;
        this.name = name;
        this.locationId = locationId;
        this.managerId = managerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", locationId=" + locationId + ", managerId=" + managerId + '}';
    }

}
