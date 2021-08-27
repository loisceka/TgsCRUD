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
    static Scanner scan = new Scanner(System.in);

    static DBConnection dbc = new DBConnection();
    static RegionDAO rdao = new RegionDAO(dbc.getConnection());
    static CountryDAO cdao = new CountryDAO(dbc.getConnection());
    static LocationDAO ldao = new LocationDAO(dbc.getConnection());
    static JobDAO jdao = new JobDAO(dbc.getConnection());
    static EmployeeDAO empdao = new EmployeeDAO(dbc.getConnection());
    static DepartmentDAO depdao = new DepartmentDAO(dbc.getConnection());

    public static void main(String[] args) throws ParseException {

        int pil = 0;

        menu1:
        while (true) {
            menu1();
            System.out.print("Masukkan Pilihan : ");
            pil = scan.nextInt();
            System.out.println("=====================");

            if (pil == 1) {
                employee:
                while (true) {
                    System.out.println("Tabel Employee");
                    menu2();
                    System.out.print("Masukkan Pilihan : ");
                    pil = scan.nextInt();
                    System.out.println("=====================");
                    tbEmployee(pil);
                    if (pil == 6) {
                        continue menu1;
                    }
                    System.out.println("======================");
                }
            }
            if (pil == 2) {
                department:
                while (true) {
                    System.out.println("Tabel Department");
                    menu2();
                    System.out.print("Masukkan Pilihan : ");
                    pil = scan.nextInt();
                    System.out.println("=====================");
                    tbDepartment(pil);
                    if (pil == 6) {
                        continue menu1;
                    }
                    System.out.println("======================");
                }
            }
            if (pil == 3) {
                job:
                while (true) {
                    System.out.println("Tabel Job");
                    menu2();
                    System.out.print("Masukkan Pilihan : ");
                    pil = scan.nextInt();
                    System.out.println("=====================");
                    tbJob(pil);
                    if (pil == 6) {
                        continue menu1;
                    }
                    System.out.println("======================");
                }
            }

            if (pil == 4) {
                location:
                while (true) {
                    System.out.println("Tabel Location");
                    menu2();
                    System.out.print("Masukkan Pilihan : ");
                    pil = scan.nextInt();
                    System.out.println("=====================");
                    tbLocation(pil);
                    if (pil == 6) {
                        continue menu1;
                    }

                    System.out.println("======================");
                }
            }
            if (pil == 5) {
                country:
                while (true) {
                    System.out.println("Tabel Country");
                    menu2();
                    System.out.print("Masukkan Pilihan : ");
                    pil = scan.nextInt();
                    System.out.println("=====================");
                    tbCountry(pil);
                    if (pil == 6) {
                        continue menu1;
                    }
                    System.out.println("======================");
                }
            }
            if (pil == 6) {
                region:
                while (true) {
                    System.out.println("Tabel Region");
                    menu2();
                    System.out.print("Masukkan Pilihan : ");
                    pil = scan.nextInt();
                    System.out.println("=====================");
                    tbRegion(pil);
                    if (pil == 6) {
                        continue menu1;
                    }

                    System.out.println("======================");
                }
            }
            if (pil == 9) {
                System.exit(0);
            }

        }

    }

    public static void tbEmployee(int pilihan) throws ParseException {
        String dateString;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (pilihan == 1) {
            for (Employee emp : empdao.getAll()) {
                System.out.println(emp.toString());
            }
        } else if (pilihan == 2) {
            System.out.println("Masukkan ID yang dicari");
            String ids = "";
            Employee emp = empdao.getById(ids = scan.next());
            System.out.println((Objects.isNull(emp)) ? "Tidak Ada dengan Data ID " + ids : emp.toString());
        } else if (pilihan == 3) {
            Employee emp = new Employee();
            System.out.println("ID: ");
            emp.setId(scan.next());
            System.out.println("First Name: ");
            emp.setFirstName(scan.nextLine());
            System.out.println("Last Name: ");
            emp.setLastName(scan.nextLine());
            System.out.println("Email: ");
            emp.setEmail(scan.next());
            System.out.println("Phone: ");
            emp.setPhone(scan.next());
            System.out.println("Hire Date: ");
            dateString = scan.nextLine();
            emp.setHireDate((Date) formatter.parse(dateString));
            System.out.println("Salary: ");
            emp.setSalary(scan.nextInt());
            System.out.println("Commision: ");
            emp.setCommision(scan.nextDouble());
            System.out.println("Job ID: ");
            emp.setJobId(scan.nextLine());
            System.out.println("Manager ID: ");
            emp.setManagerId(scan.nextLine());
            System.out.println("Department ID: ");
            emp.setManagerId(scan.nextLine());
            empdao.save(emp);
        } else if (pilihan == 4) {
            Employee emp = new Employee();
            System.out.print("Masukkan ID : ");
            emp.setId(scan.next());
            if (Objects.isNull(empdao.getById(emp.getId()))) {
                System.out.println("Tidak Ada Data dengan ID " + emp.getId());
            } else {
                System.out.println("First Name: ");
                emp.setFirstName(scan.nextLine());
                System.out.println("Last Name: ");
                emp.setLastName(scan.nextLine());
                System.out.println("Email: ");
                emp.setEmail(scan.next());
                System.out.println("Phone: ");
                emp.setPhone(scan.next());
                System.out.println("Hire Date: ");
                dateString = scan.nextLine();
                emp.setHireDate((Date) formatter.parse(dateString));
                System.out.println("Salary: ");
                emp.setSalary(scan.nextInt());
                System.out.println("Commision: ");
                emp.setCommision(scan.nextDouble());
                System.out.println("Job ID: ");
                emp.setJobId(scan.nextLine());
                System.out.println("Manager ID: ");
                emp.setManagerId(scan.nextLine());
                System.out.println("Department ID: ");
                emp.setManagerId(scan.nextLine());
                empdao.save(emp);
            }
        } else if (pilihan == 5) {
            System.out.print("Masukkan ID : ");
            empdao.delete(scan.next());
        }

    }

    public static void tbDepartment(int pilihan) {
        if (pilihan == 1) {
            for (Department dep : depdao.getAll()) {
                System.out.println(dep.toString());
            }
        } else if (pilihan == 2) {
            System.out.println("Masukkan ID yang dicari");
            String ids = "";
            Department dep = depdao.getById(ids = scan.next());
            System.out.println((Objects.isNull(dep)) ? "Tidak Ada dengan Data ID " + ids : dep.toString());
        } else if (pilihan == 3) {
            Department dep = new Department();
            System.out.println("ID: ");
            dep.setId(scan.next());
            System.out.println("Department Name: ");
            dep.setName(scan.nextLine());
            System.out.println("Location ID: ");
            dep.setLocationId(scan.nextLine());
            System.out.println("Manager ID: ");
            dep.setManagerId(scan.nextLine());
            depdao.save(dep);
        } else if (pilihan == 4) {
            Department dep = new Department();
            System.out.print("Masukkan ID : ");
            dep.setId(scan.next());
            if (Objects.isNull(jdao.getById(dep.getId()))) {
                System.out.println("Tidak Ada Data dengan ID " + dep.getId());
            } else {
                System.out.println("Department Name: ");
                dep.setName(scan.nextLine());
                System.out.println("Location ID: ");
                dep.setLocationId(scan.nextLine());
                System.out.println("Manager ID: ");
                dep.setManagerId(scan.nextLine());
                depdao.save(dep);
            }
        } else if (pilihan == 5) {
            System.out.print("Masukkan ID : ");
            depdao.delete(scan.next());
        }
    }

    public static void tbJob(int pilihan) {
        if (pilihan == 1) {
            for (Job job : jdao.getAll()) {
                System.out.println(job.toString());
            }
        } else if (pilihan == 2) {
            System.out.println("Masukkan ID yang dicari");
            String ids = "";
            Job job = jdao.getById(ids = scan.next());
            System.out.println((Objects.isNull(job)) ? "Tidak Ada dengan Data ID " + ids : job.toString());
        } else if (pilihan == 3) {
            Job job = new Job();
            System.out.println("ID: ");
            job.setId(scan.next());
            System.out.println("Job Name: ");
            job.setName(scan.nextLine());
            System.out.println("Min Salary: ");
            job.setMinSalary(scan.nextInt());
            System.out.println("Max Salary: ");
            job.setMaxSalary(scan.nextInt());
            jdao.save(job);
        } else if (pilihan == 4) {
            Job job = new Job();
            System.out.print("Masukkan ID : ");
            job.setId(scan.next());
            if (Objects.isNull(jdao.getById(job.getId()))) {
                System.out.println("Tidak Ada Data dengan ID " + job.getId());
            } else {
                System.out.println("Job Name: ");
                job.setName(scan.next());
                System.out.println("Min Salary: ");
                job.setMinSalary(scan.nextInt());
                System.out.println("Max Salary: ");
                job.setMaxSalary(scan.nextInt());
                jdao.save(job);
            }
        } else if (pilihan == 5) {
            System.out.print("Masukkan ID : ");
            jdao.delete(scan.next());
        }
    }

    public static void tbLocation(int pilihan) {
        if (pilihan == 1) {

            for (Location location : ldao.getAll()) {
                System.out.println(location.toString());

            }
        } else if (pilihan == 2) {
            System.out.println("Masukkan ID yang dicari");
            String ids = "";
            Location l = ldao.getById(ids = scan.next());
            System.out.println((Objects.isNull(l)) ? "Tidak Ada dengan Data ID " + ids : l.toString());
        } else if (pilihan == 3) {
            Location c = new Location();
            System.out.print("Masukkan ID : ");
            c.setId(scan.next());
            System.out.print("Masukkan Street Adress : ");
            c.setStreet(scan.next());
            System.out.print("Masukkan Postal Code : ");
            c.setPostalCode(scan.next());
            System.out.print("Masukkan City : ");
            c.setCity(scan.next());
            System.out.print("Masukkan State Province : ");
            c.setProvince(scan.next());
            System.out.print("Masukkan Country ID : ");
            c.setCountry(scan.next());
            ldao.insertAndUpdate(c);
        } else if (pilihan == 4) {
            Location c = new Location();
            System.out.print("Masukkan ID : ");
            c.setId(scan.next());
            if (Objects.isNull(ldao.getById(c.getId()))) {
                System.out.println("Tidak Ada Data dengan ID " + c.getId());
            } else {
                System.out.print("Masukkan Street Adress : ");
                c.setStreet(scan.next());
                System.out.print("Masukkan Postal Code : ");
                c.setPostalCode(scan.next());
                System.out.print("Masukkan City : ");
                c.setCity(scan.next());
                System.out.print("Masukkan State Province : ");
                c.setProvince(scan.next());
                System.out.print("Masukkan Country ID : ");
                c.setCountry(scan.next());
                ldao.insertAndUpdate(c);

            }
        } else if (pilihan == 5) {
            System.out.print("Masukkan ID : ");
            ldao.delete(scan.next());
        }

    }

    public static void tbCountry(int pilihan) {
        if (pilihan == 1) {
            for (Country country : cdao.getAll()) {
                System.out.println(country.toString());
            }
        } else if (pilihan == 2) {
            System.out.println("Masukkan ID yang dicari");
            String ids = "";
            Country c = cdao.getById(ids = scan.next());
            System.out.println((Objects.isNull(c)) ? "Tidak Ada dengan Data ID " + ids : c.toString());
        } else if (pilihan == 3) {
            Country c = new Country();
            System.out.print("Masukkan ID : ");
            c.setId(scan.next());
            System.out.print("Masukkan Nama : ");
            c.setName(scan.next());
            System.out.print("Masukkan ID Region : ");
            c.setRegion(scan.nextInt());
            cdao.insertAndUpdate(c);
        } else if (pilihan == 4) {
            Country c = new Country();
            System.out.print("Masukkan ID : ");
            c.setId(scan.next());
            if (Objects.isNull(cdao.getById(c.getId()))) {
                System.out.println("Tidak Ada Data dengan ID " + c.getId());
            } else {
                System.out.print("Masukkan Nama : ");
                String name = scan.next();
                c.setName(name);
                System.out.print("Masukkan ID Region : ");
                int r = scan.nextInt();
                c.setRegion(r);
                cdao.insertAndUpdate(c);
            }
        } else if (pilihan == 5) {
            System.out.print("Masukkan ID : ");
            cdao.delete(scan.next());
        }
    }

    public static void tbRegion(int pilihan) {
        if (pilihan == 1) {
            for (Region region : rdao.getAll()) {
                System.out.println(region.toString());
            }
        } else if (pilihan == 2) {
            System.out.println("Masukkan ID yang dicari");
            int ids = 0;
            Region r = rdao.getById(ids = scan.nextInt());
            System.out.println((Objects.isNull(r)) ? "Tidak Ada dengan Data ID " + ids : r.toString());
        } else if (pilihan == 3) {
            Region r = new Region();
            System.out.print("Masukkan ID : ");
            r.setId(scan.nextInt());
            System.out.print("Masukkan Nama : ");
            r.setName(scan.next());
            rdao.insertAndUpdate(r);
        } else if (pilihan == 4) {
            Region r = new Region();
            System.out.print("Masukkan ID : ");
            r.setId(scan.nextInt());
            if (Objects.isNull(rdao.getById(r.getId()))) {
                System.out.println("Tidak Ada Data dengan ID " + r.getId());
            } else {
                System.out.print("Masukkan Nama : ");
                String name = scan.next();
                r.setName(name);
                rdao.insertAndUpdate(r);
            }
        } else if (pilihan == 5) {
            System.out.print("Masukkan ID : ");
            rdao.delete(scan.nextInt());
        }

    }

    public static void menu1() throws ParseException {
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
    }
}
