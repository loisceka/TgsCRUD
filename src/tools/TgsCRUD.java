/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import daos.CountryDAO;
import daos.LocationDAO;
import daos.RegionDAO;
import daos.DepartmentDAO;
import daos.EmployeeDAO;
import daos.JobDAO;
import static java.awt.SystemColor.menu;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import models.Department;
import models.Employee;
import models.Job;
import models.Region;
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
    public static void main(String[] args) throws ParseException {
        //Connection
        DBConnection dbc = new DBConnection();
        //Test Connection
        System.out.println(dbc.getConnection());

        //Dependency Injection
//        RegionDAO rdao = new RegionDAO(dbc.getConnection());
//        CountryDAO cdao = new CountryDAO(dbc.getConnection());
//        LocationDAO ldao = new LocationDAO(dbc.getConnection());
//        JobDAO jdao = new JobDAO(dbc.getConnection());
//        EmployeeDAO empdao = new EmployeeDAO(dbc.getConnection());
//        DepartmentDAO depdao = new DepartmentDAO(dbc.getConnection());

//        menu1();

    }

    public static void tbEmployee() throws ParseException {
        Scanner scan = new Scanner(System.in);
        String fungsi = null;
        String id = null;
        String newId, firstName, lastName, email, phone, jobId, managerId, departmentId, dateString;
        Date hireDate;
        int salary;
        double commision;
        DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
        //Connection
        DBConnection dbc = new DBConnection();
        //Dependency
        EmployeeDAO empdao = new EmployeeDAO(dbc.getConnection());
        System.out.println("Employee Table Menu : ");
        menu2();
        do {
            fungsi = scan.nextLine();
            switch (fungsi) {
                case "1":
                    for (Employee emp : empdao.getAll()) {
                        System.out.println(emp.getId() + " - " + emp.getFirstName() + " " + emp.getLastName() + " - " + emp.getEmail() + " - "
                                + emp.getPhone() + " - " + emp.getHireDate() + " - " + emp.getSalary() + " - " + emp.getCommision() + " - "
                                + emp.getJobId() + " - " + emp.getManagerId() + " - " + emp.getDepartmentId());
                    }
                    break;
                case "2":
                    System.out.println("GET BY ID MENU");
                    System.out.println("Insert the ID: ");
                    id = scan.nextLine();
                    System.out.println(empdao.getById(id));
                    break;
                case "3":
                    System.out.println("INSERT DATA");
                    System.out.println("ID: ");
                    id = scan.nextLine();
                    System.out.println("First Name: ");
                    firstName = scan.nextLine();
                    System.out.println("Last Name: ");
                    lastName = scan.nextLine();
                    System.out.println("Email: ");
                    email = scan.nextLine();
                    System.out.println("Phone: ");
                    phone = scan.nextLine();
                    System.out.println("Hire Date: ");
                    dateString = scan.nextLine();
                    hireDate = (Date) formatter.parse(dateString);
                    System.out.println("Salary: ");
                    salary = scan.nextInt();
                    System.out.println("Commision: ");
                    commision = scan.nextDouble();
                    System.out.println("Job ID: ");
                    jobId = scan.nextLine();
                    System.out.println("Manager ID: ");
                    managerId = scan.nextLine();
                    System.out.println("Department ID: ");
                    departmentId = scan.nextLine();
                    empdao.insert(new Employee(id, firstName, lastName, email, phone, hireDate, salary, commision, jobId, managerId, departmentId));
                    System.out.println("Insert Success !");
                    break;
                case "4":
                    System.out.println("ID: ");
                    id = scan.nextLine();
                    if (empdao.getById(id) != null) {
                        System.out.println("UPDATE DATA");
                        System.out.println("ID: ");
                        newId = scan.nextLine();
                        System.out.println("First Name: ");
                        firstName = scan.nextLine();
                        System.out.println("Last Name: ");
                        lastName = scan.nextLine();
                        System.out.println("Email: ");
                        email = scan.nextLine();
                        System.out.println("Phone: ");
                        phone = scan.nextLine();
                        System.out.println("Hire Date: ");
                        dateString = scan.nextLine();
                        hireDate = (Date) formatter.parse(dateString);
                        System.out.println("Salary: ");
                        salary = scan.nextInt();
                        System.out.println("Commision: ");
                        commision = scan.nextDouble();
                        System.out.println("Job ID: ");
                        jobId = scan.nextLine();
                        System.out.println("Manager ID: ");
                        managerId = scan.nextLine();
                        System.out.println("Department ID: ");
                        departmentId = scan.nextLine();
                        empdao.update(id, new Employee(newId, firstName, lastName, email, phone, hireDate, salary, commision, jobId, managerId, departmentId));
                        System.out.println("Update Success !");
                    } else {
                        System.out.println("ID IS INVALID !");
                    }
                    break;
                case "5":
                    System.out.println("DELETE DATA");
                    System.out.println("ID: ");
                    id = scan.nextLine();
                    if (empdao.getById(id) != null) {
                        empdao.delete(id);
                        System.out.println("Data Deleted !");
                    } else {
                        System.out.println("ID IS INVALID");
                    }
                    break;
                case "6":
                    menu1();
                    break;
            }
        } while (!menu.equals("0"));

    }

    public static void tbDepartment() throws ParseException {
        Scanner scan = new Scanner(System.in);
        String fungsi = null;
        String id = null;
        String newId, name, locationId, managerId;
        //Connection
        DBConnection dbc = new DBConnection();
        //Dependency
        DepartmentDAO depdao = new DepartmentDAO(dbc.getConnection());
        System.out.println("Department Table Menu : ");
        menu2();
        do {
            fungsi = scan.nextLine();
            switch (fungsi) {
                case "1":
                    for (Department dep : depdao.getAll()) {
                        System.out.println(dep.getId() + " - " + dep.getName() + " - " + dep.getLocationId() + " - " + dep.getManagerId());
                    }
                    break;
                case "2":
                    System.out.println("GET BY ID MENU");
                    System.out.println("Insert the ID: ");
                    id = scan.nextLine();
                    System.out.println(depdao.getById(id));
                    break;
                case "3":
                    System.out.println("INSERT DATA");
                    System.out.println("ID: ");
                    id = scan.nextLine();
                    System.out.println("Department Name: ");
                    name = scan.nextLine();
                    System.out.println("Location ID: ");
                    locationId = scan.nextLine();
                    System.out.println("Manager ID: ");
                    managerId = scan.nextLine();
                    depdao.insert(new Department(id, name, locationId, managerId));
                    System.out.println("Insert Success !");
                    break;
                case "4":
                    System.out.println("ID: ");
                    id = scan.nextLine();
                    if (depdao.getById(id) != null) {
                        System.out.println("UPDATE DATA");
                        System.out.println("ID: ");
                        newId = scan.nextLine();
                        System.out.println("Department Name: ");
                        name = scan.nextLine();
                        System.out.println("Location ID: ");
                        locationId = scan.nextLine();
                        System.out.println("Manager ID: ");
                        managerId = scan.nextLine();
                        depdao.update(id, new Department(newId, name, locationId, managerId));
                        System.out.println("Update Success !");
                    } else {
                        System.out.println("INVALID ID");
                    }
                    break;
                case "5":
                    System.out.println("DELETE DATA");
                    System.out.println("ID: ");
                    id = scan.nextLine();
                    if (depdao.getById(id) != null) {
                        depdao.delete(id);
                        System.out.println("Data Deleted !");
                    } else {
                        System.out.println("ID IS INVALID");
                    }
                    break;
                case "6":
                    menu1();
                    break;
            }
        } while (!menu.equals("0"));
    }

    public static void tbJob() throws ParseException {
        Scanner scan = new Scanner(System.in);
        String fungsi = null;
        String id = null;
        String newId, name;
        int minSalary, maxSalary;
        //Connection
        DBConnection dbc = new DBConnection();
        //Dependency
        JobDAO jdao = new JobDAO(dbc.getConnection());
        System.out.println("Job Table Menu : ");
        menu2();
        do {
            fungsi = scan.nextLine();
            switch (fungsi) {
                case "1":
                    for (Job job : jdao.getAll()) {
                        System.out.println(job.getId() + " - " + job.getName() + " - " + job.getMinSalary() + " - " + job.getMaxSalary());
                    }
                    break;
                case "2":
                    System.out.println("GET BY ID MENU");
                    System.out.println("Insert the ID: ");
                    id = scan.nextLine();
                    System.out.println(jdao.getById(id));
                    break;
                case "3":
                    System.out.println("INSERT DATA");
                    System.out.println("ID: ");
                    id = scan.nextLine();
                    System.out.println("Job Name: ");
                    name = scan.nextLine();
                    System.out.println("Min Salary: ");
                    minSalary = scan.nextInt();
                    System.out.println("Max Salary: ");
                    maxSalary = scan.nextInt();
                    jdao.insert(new Job(id, name, minSalary, maxSalary));
                    System.out.println("Insert Success !");
                    break;
                case "4":
                    System.out.println("ID: ");
                    id = scan.nextLine();
                    if (jdao.getById(id) != null) {
                        System.out.println("UPDATE DATA");
                        System.out.println("ID: ");
                        newId = scan.nextLine();
                        System.out.println("Job Name: ");
                        name = scan.nextLine();
                        System.out.println("Min Salary: ");
                        minSalary = scan.nextInt();
                        System.out.println("Max Salary: ");
                        maxSalary = scan.nextInt();
                        jdao.update(id, new Job(newId, name, minSalary, maxSalary));
                        System.out.println("Update Success !");
                    } else {
                        System.out.println("INVALID ID");
                    }
                    break;
                case "5":
                    System.out.println("DELETE DATA");
                    System.out.println("ID: ");
                    id = scan.nextLine();
                    if (jdao.getById(id) != null) {
                        jdao.delete(id);
                        System.out.println("Data Deleted !");
                    } else {
                        System.out.println("ID IS INVALID");
                    }
                    break;
                case "6":
                    menu1();
                    break;
            }
        } while (!menu.equals("0"));

    }

    public static void tbLocation() {
        System.out.println("Location Table Menu : ");
        menu2();
    }

    public static void tbCountry() {
        System.out.println("Country Table Menu : ");
        menu2();
    }

    public static void tbRegion() {
        System.out.println("Region Table Menu : ");
        menu2();
    }

    public static void menu1() throws ParseException {
        String menu = null;
        Scanner scan = new Scanner(System.in);
        System.out.println("HR Database Table Menu : ");
        System.out.println("1. Employee");
        System.out.println("2. Department");
        System.out.println("3. Job");
        System.out.println("4. Location");
        System.out.println("5. Country");
        System.out.println("6. Region");
        System.out.println("0. Exit");
        System.out.println("Your Choice : ");
        do {
            menu = scan.nextLine();
            switch (menu) {
                case "1":
                    tbEmployee();
                    break;
                case "2":
                    tbDepartment();
                    break;
                case "3":
                    tbJob();
                    break;
                case "4":
                    tbLocation();
                    break;
                case "5":
                    tbCountry();
                    break;
                case "6":
                    tbRegion();
                    break;
            }
        } while (!menu.equals("0"));
    }

    public static void menu2() {
        System.out.println("1. Read All");
        System.out.println("2. Get By ID");
        System.out.println("3. Insert Data");
        System.out.println("4. Update Data");
        System.out.println("5. Delete Data");
        System.out.println("6. Back");
        System.out.println("0. Exit");
        System.out.println("Your Choice : ");
    }
}
