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
public class Region {

    private int id;
    private String name;

    public Region() {
    }

    public Region(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int RegionId) {
        this.id = RegionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String RegionName) {
        this.name = RegionName;
    }

    @Override
    public String toString() {
        return id + "-"+ name;
    }
}
