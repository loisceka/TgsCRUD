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
import java.util.Objects;
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
      //Connection
        
    /**
     * @param args the command line arguments
     */
        static Scanner scan = new Scanner(System. in);
        
        static DBConnection dbc = new DBConnection();
        static RegionDAO rdao = new RegionDAO(dbc.getConnection());
        static CountryDAO cdao = new CountryDAO(dbc.getConnection());
        static LocationDAO ldao = new LocationDAO(dbc.getConnection());
        static JobDAO jdao = new JobDAO(dbc.getConnection());
        static EmployeeDAO empdao = new EmployeeDAO(dbc.getConnection());
        static DepartmentDAO depdao = new DepartmentDAO(dbc.getConnection());
    public static void main(String[] args) throws ParseException {
        
        int pil = 0;
   
        menu1 : while(true){
            menu1();
            System.out.print("Masukkan Pilihan : ");
            pil  = scan.nextInt();
            System.out.println("=====================");
            if(pil == 5){
                country: while(true){
                    System.out.println("Tabel Country");
                    menu2();
                    System.out.print("Masukkan Pilihan : ");
                    pil  = scan.nextInt();
                    System.out.println("=====================");
                    tbCountry(pil);
                    if(pil == 6){
                        continue menu1;
                    }
                    System.out.println("======================");
                }
            }
            if(pil == 6){
                region: while(true){
                    System.out.println("Tabel Region");
                    menu2();
                    System.out.print("Masukkan Pilihan : ");
                    pil  = scan.nextInt();
                    System.out.println("=====================");
                    tbRegion(pil);
                    if(pil == 6){
                        continue menu1;
                    }
                    
                    System.out.println("======================");
                }
            }
            if(pil == 9){
                System.exit(0);
            }
            
        }
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

    public static void tbLocation(int pilihan) {
        if(pilihan == 1){
            for(Country country : cdao.getAll()){
                System.out.println(country.toString());    
            }
        }
        else if(pilihan == 2){
           System.out.println("Masukkan ID yang dicari");
           String ids = "";
           Country c = cdao.getById(ids = scan.next());
            System.out.println((Objects.isNull(c)) ? "Tidak Ada dengan Data ID " + ids : c.toString());
        }
        else if(pilihan ==  3){
            Country c = new Country();
            System.out.print("Masukkan ID : "  );
            c.setId(scan.next());
            System.out.print("Masukkan Nama : "  );
            c.setName(scan.next());
            System.out.print("Masukkan ID Region : "  );
            c.setRegion(scan.nextInt());
            cdao.insertAndUpdate(c);
        }
        else if(pilihan ==  4){
            Country c= new Country();
            System.out.print("Masukkan ID : "  );
            c.setId(scan.next());
            if(Objects.isNull(cdao.getById(c.getId()))){
                System.out.println("Tidak Ada Data dengan ID " + c.getId());
            }
            else{
                System.out.print("Masukkan Nama : "  );
                String name = scan.next();
                c.setName(name);
                System.out.print("Masukkan ID Region : "  );
                int r = scan.nextInt();
                c.setRegion(r);
                cdao.insertAndUpdate(c);
            }
        }
        else if(pilihan ==  5){
            System.out.print("Masukkan ID : ");
            cdao.delete(scan.next());
        }
        
    }

    public static void tbCountry(int pilihan) {
        if(pilihan == 1){
            for(Country country : cdao.getAll()){
                System.out.println(country.toString());    
            }
        }
        else if(pilihan == 2){
           System.out.println("Masukkan ID yang dicari");
           String ids = "";
           Country c = cdao.getById(ids = scan.next());
            System.out.println((Objects.isNull(c)) ? "Tidak Ada dengan Data ID " + ids : c.toString());
        }
        else if(pilihan ==  3){
            Country c = new Country();
            System.out.print("Masukkan ID : "  );
            c.setId(scan.next());
            System.out.print("Masukkan Nama : "  );
            c.setName(scan.next());
            System.out.print("Masukkan ID Region : "  );
            c.setRegion(scan.nextInt());
            cdao.insertAndUpdate(c);
        }
        else if(pilihan ==  4){
            Country c= new Country();
            System.out.print("Masukkan ID : "  );
            c.setId(scan.next());
            if(Objects.isNull(cdao.getById(c.getId()))){
                System.out.println("Tidak Ada Data dengan ID " + c.getId());
            }
            else{
                System.out.print("Masukkan Nama : "  );
                String name = scan.next();
                c.setName(name);
                System.out.print("Masukkan ID Region : "  );
                int r = scan.nextInt();
                c.setRegion(r);
                cdao.insertAndUpdate(c);
            }
        }
        else if(pilihan ==  5){
            System.out.print("Masukkan ID : ");
            cdao.delete(scan.next());
        }
    }

    public static void tbRegion(int pilihan) {
        if(pilihan == 1){
            for(Region region : rdao.getAll()){
                System.out.println(region.toString());    
            }
        }
        else if(pilihan == 2){
           System.out.println("Masukkan ID yang dicari");
           int ids = 0;
           Region r = rdao.getById(ids = scan.nextInt());
            System.out.println((Objects.isNull(r)) ? "Tidak Ada dengan Data ID " + ids : r.toString());
        }
        else if(pilihan ==  3){
            Region r = new Region();
            System.out.print("Masukkan ID : "  );
            r.setId(scan.nextInt());
            System.out.print("Masukkan Nama : "  );
            r.setName(scan.next());
            rdao.insertAndUpdate(r);
        }
        else if(pilihan ==  4){
            Region r = new Region();
            System.out.print("Masukkan ID : "  );
            r.setId(scan.nextInt());
            if(Objects.isNull(rdao.getById(r.getId()))){
                System.out.println("Tidak Ada Data dengan ID " + r.getId());
            }
            else{
                System.out.print("Masukkan Nama : "  );
                String name = scan.next();
                r.setName(name);
                rdao.insertAndUpdate(r);
            }
        }
        else if(pilihan ==  5){
            System.out.print("Masukkan ID : ");
            rdao.delete(scan.nextInt());
        }
        
    }

    public static void menu1() throws ParseException {
        Scanner scan = new Scanner(System.in);
        System.out.println("HR Database Table Menu : ");
        System.out.println("1. Employee");
        System.out.println("2. Department");
        System.out.println("3. Job");
        System.out.println("4. Location");
        System.out.println("5. Country");
        System.out.println("6. Region");
        System.out.println("9. Exit");
        
    }

    public static void menu2() {
        System.out.println("1. Read All");
        System.out.println("2. Get By ID");
        System.out.println("3. Insert Data");
        System.out.println("4. Update Data");
        System.out.println("5. Delete Data");
        System.out.println("6. Back");
        System.out.println("0. Exit");
    }
}
