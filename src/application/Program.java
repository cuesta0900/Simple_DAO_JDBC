package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("=== TEST 1: SELLER find by id ===");
        Seller seller = sellerDao.selectById(3);
        System.out.println(seller);

        System.out.println("\n=== TEST 2: SELLER find by Department ===");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        list.forEach(System.out::println);

        System.out.println("\n=== TEST 3: SELLER find All ===");
        list = sellerDao.selectAll();
        list.forEach(System.out::println);

        /*System.out.println("\n=== TEST 4: SELLER INSERT ===");
        Seller newSeller = new Seller(null, "Pedro", "pedro@gmail.com", new Date(), 6500.00, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted new Id = " + newSeller.getId());*/

        System.out.println("\n=== TEST 5: SELLER UPDATE ===");
        seller = sellerDao.selectById(1);
        seller.setName("Matha Wayne");
        sellerDao.update(seller);
        System.out.println("UPDATED");

        System.out.println("\n=== TEST 5: SELLER DELETE ===");
        System.out.println("Enter a code to delete");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("DELETED!");



    }

}
