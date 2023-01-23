package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramDepartment {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        System.out.println("=== TEST 1: DEPARTMENT find by id ===");
        Department department = departmentDao.selectById(4);
        System.out.println(department);

        System.out.println("\n=== TEST 2: SELLER find All ===");
        List<Department> list = new ArrayList<>();
        list = departmentDao.selectAll();
        list.forEach(System.out::println);

        /*System.out.println("\n=== TEST 3: DEPARTMENT INSERT ===");
        Department newDep = new Department(null, "IT");
        departmentDao.insert(newDep);
        System.out.println("Inserted new Id = " + newDep.getId());*/

        System.out.println("\n=== TEST 4: DEPARTMENT UPDATE ===");
        department = departmentDao.selectById(1);
        department.setName("HR");
        departmentDao.update(department);
        System.out.println("UPDATED");

        System.out.println("\n=== TEST 5: DEPARTMENT DELETE ===");
        System.out.println("Enter a code to delete");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("DELETED!");
    }
}
